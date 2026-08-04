package org.cloudburstmc.protocol.bedrock.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.data.ServerSoundHandle;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.payload.common.GatheringsConfigurationJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerId;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.abilities.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.ExperimentToggle;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.Experiments;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureSettings;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimationData;
import org.cloudburstmc.protocol.bedrock.data.skin.ImageData;
import org.cloudburstmc.protocol.bedrock.data.skin.Skin;
import org.cloudburstmc.protocol.common.DefinitionRegistry;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.function.*;

import static java.util.Objects.requireNonNull;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseBedrockCodecHelper implements BedrockCodecHelper {
    protected static final InternalLogger log = InternalLoggerFactory.getInstance(BaseBedrockCodecHelper.class);

    protected final ActorDataTypeMap actorData;
    protected final TypeMap<Class<?>> gameRuleType;

    @Getter
    @Setter
    protected DefinitionRegistry<ItemDefinition> itemDefinitions;
    @Getter
    @Setter
    protected DefinitionRegistry<BlockDefinition> blockDefinitions;

    @Getter
    @Setter
    protected EncodingSettings encodingSettings = EncodingSettings.DEFAULT;

    protected static boolean isAir(ItemDefinition definition) {
        return definition == null || "minecraft:air".equals(definition.getIdentifier());
    }

    @Override
    public byte[] readByteArray(ByteBuf buffer) {
        return this.readByteArray(buffer, this.encodingSettings.maxByteArraySize());
    }

    public byte[] readByteArray(ByteBuf buffer, int maxLength) {
        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(buffer.isReadable(length),
                "Tried to read %s bytes but only has %s readable", length, buffer.readableBytes());
        checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s bytes but maximum is %s", length, maxLength);
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return bytes;
    }

    public void writeByteArray(ByteBuf buffer, byte[] bytes) {
        checkNotNull(bytes, "bytes");
        VarInts.writeUnsignedInt(buffer, bytes.length);
        buffer.writeBytes(bytes);
    }

    @Override
    public ByteBuf readByteBuf(ByteBuf buffer) {
        int length = VarInts.readUnsignedInt(buffer);
        return buffer.readRetainedSlice(length);
    }

    @Override
    public void writeByteBuf(ByteBuf buffer, ByteBuf toWrite) {
        checkNotNull(toWrite, "toWrite");
        VarInts.writeUnsignedInt(buffer, toWrite.readableBytes());
        buffer.writeBytes(toWrite, toWrite.readerIndex(), toWrite.writerIndex());
    }

    public String readString(ByteBuf buffer) {
        return this.readStringMaxLen(buffer, this.encodingSettings.maxStringLength());
    }

    @Override
    public String readStringMaxLen(ByteBuf buffer, int maxLength) {
        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(maxLength <= 0 || length <= maxLength,
                "Tried to read %s bytes but maximum is %s", length, maxLength);
        return (String) buffer.readCharSequence(length, StandardCharsets.UTF_8);
    }

    public void writeString(ByteBuf buffer, String string) {
        checkNotNull(string, "string");
        VarInts.writeUnsignedInt(buffer, ByteBufUtil.utf8Bytes(string));
        buffer.writeCharSequence(string, StandardCharsets.UTF_8);
    }

    public UUID readUuid(ByteBuf buffer) {
        return new UUID(buffer.readLongLE(), buffer.readLongLE());
    }

    public void writeUuid(ByteBuf buffer, UUID uuid) {
        checkNotNull(uuid, "uuid");
        buffer.writeLongLE(uuid.getMostSignificantBits());
        buffer.writeLongLE(uuid.getLeastSignificantBits());
    }

    public Vector3f readVector3f(ByteBuf buffer) {
        float x = buffer.readFloatLE();
        float y = buffer.readFloatLE();
        float z = buffer.readFloatLE();
        return Vector3f.from(x, y, z);
    }

    public void writeVector3f(ByteBuf buffer, Vector3f vector3f) {
        checkNotNull(vector3f, "vector3f");
        buffer.writeFloatLE(vector3f.getX());
        buffer.writeFloatLE(vector3f.getY());
        buffer.writeFloatLE(vector3f.getZ());
    }

    public Vector2f readVector2f(ByteBuf buffer) {
        float x = buffer.readFloatLE();
        float y = buffer.readFloatLE();
        return Vector2f.from(x, y);
    }

    public void writeVector2f(ByteBuf buffer, Vector2f vector2f) {
        checkNotNull(vector2f, "vector2f");
        buffer.writeFloatLE(vector2f.getX());
        buffer.writeFloatLE(vector2f.getY());
    }


    public Vector3i readVector3i(ByteBuf buffer) {
        int x = VarInts.readInt(buffer);
        int y = VarInts.readInt(buffer);
        int z = VarInts.readInt(buffer);

        return Vector3i.from(x, y, z);
    }

    public void writeVector3i(ByteBuf buffer, Vector3i vector3i) {
        checkNotNull(vector3i, "vector3i");
        VarInts.writeInt(buffer, vector3i.getX());
        VarInts.writeInt(buffer, vector3i.getY());
        VarInts.writeInt(buffer, vector3i.getZ());
    }

    @Override
    public float readByteAngle(ByteBuf buffer) {
        return buffer.readByte() * (360f / 256f);
    }

    @Override
    public void writeByteAngle(ByteBuf buffer, float angle) {
        buffer.writeByte((byte) (angle / (360f / 256f)));
    }

    public Vector3i readBlockPosition(ByteBuf buffer) {
        int x = VarInts.readInt(buffer);
        int y = VarInts.readUnsignedInt(buffer);
        int z = VarInts.readInt(buffer);

        return Vector3i.from(x, y, z);
    }

    public void writeBlockPosition(ByteBuf buffer, Vector3i blockPosition) {
        checkNotNull(blockPosition, "blockPosition");
        VarInts.writeInt(buffer, blockPosition.getX());
        VarInts.writeUnsignedInt(buffer, blockPosition.getY());
        VarInts.writeInt(buffer, blockPosition.getZ());
    }

    /*
        Helper array serialization
     */

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, BiFunction<ByteBuf, BedrockCodecHelper, T> function) {
        this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
    }

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, BiFunction<ByteBuf, BedrockCodecHelper, T> function) {
        this.readArray(buffer, array, lengthReader, function, this.encodingSettings.maxListSize());
    }

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength) {
        long length = lengthReader.applyAsLong(buffer);
        checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s bytes but maximum is %s", length, maxLength);

        for (int i = 0; i < length; i++) {
            array.add(function.apply(buffer, this));
        }
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, Collection<T> array, ObjIntConsumer<ByteBuf> lengthWriter, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
        lengthWriter.accept(buffer, array.size());
        for (T val : array) {
            consumer.accept(buffer, this, val);
        }
    }

    @Override
    public <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, BedrockCodecHelper, T> function) {
        return this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
    }

    @Override
    public <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength) {
        ObjectArrayList<T> list = new ObjectArrayList<>();
        readArray(buffer, list, function, maxLength);
        return list.toArray(array);
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, T[] array, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
        VarInts.writeUnsignedInt(buffer, array.length);
        for (T val : array) {
            consumer.accept(buffer, this, val);
        }
    }
    /*
        Non-helper array serialization
     */

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function) {
        this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
    }

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function, int maxLength) {
        this.readArray(buffer, array, VarInts::readUnsignedInt, function, maxLength);
    }

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, Function<ByteBuf, T> function) {
        this.readArray(buffer, array, lengthReader, function, this.encodingSettings.maxListSize());
    }

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, Function<ByteBuf, T> function, int maxLength) {
        long length = lengthReader.applyAsLong(buffer);
        checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s bytes but maximum is %s", length, maxLength);

        for (int i = 0; i < length; i++) {
            array.add(function.apply(buffer));
        }
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, Collection<T> array, BiConsumer<ByteBuf, T> biConsumer) {
        this.writeArray(buffer, array, VarInts::writeUnsignedInt, biConsumer);
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, Collection<T> array, ObjIntConsumer<ByteBuf> lengthWriter, BiConsumer<ByteBuf, T> consumer) {
        lengthWriter.accept(buffer, array.size());
        for (T val : array) {
            consumer.accept(buffer, val);
        }
    }

    @Override
    public <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function) {
        return this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
    }

    @Override
    public <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function, int maxLength) {
        ObjectArrayList<T> list = new ObjectArrayList<>();
        readArray(buffer, list, function, maxLength);
        return list.toArray(array);
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, T[] array, BiConsumer<ByteBuf, T> biConsumer) {
        VarInts.writeUnsignedInt(buffer, array.length);
        for (T val : array) {
            biConsumer.accept(buffer, val);
        }
    }

    @Override
    public <T> T readTag(ByteBuf buffer, Class<T> expected) {
        return this.readTag(buffer, expected, this.encodingSettings.maxNetworkNBTSize());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T readTag(ByteBuf buffer, Class<T> expected, long maxReadSize) {
        try (NBTInputStream reader = NbtUtils.createNetworkReader(new ByteBufInputStream(buffer), maxReadSize)) {
            Object tag = reader.readTag();
            checkArgument(expected.isInstance(tag), "Expected tag of %s type but received %s",
                    expected, tag.getClass());
            return (T) tag;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeTag(ByteBuf buffer, Object tag) {
        try (NBTOutputStream writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer))) {
            writer.writeTag(tag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T readTagLE(ByteBuf buffer, Class<T> expected) {
        return this.readTagLE(buffer, expected, this.encodingSettings.maxNetworkNBTSize());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T readTagLE(ByteBuf buffer, Class<T> expected, long maxReadSize) {
        try (NBTInputStream reader = NbtUtils.createReaderLE(new ByteBufInputStream(buffer), maxReadSize)) {
            Object tag = reader.readTag();
            checkArgument(expected.isInstance(tag), "Expected tag of %s type but received %s",
                    expected, tag.getClass());
            return (T) reader.readTag();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTagLE(ByteBuf buffer, Object tag) {
        try (NBTOutputStream writer = NbtUtils.createWriterLE(new ByteBufOutputStream(buffer))) {
            writer.writeTag(tag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T readTagValue(ByteBuf buffer, NbtType<T> type) {
        return this.readTagValue(buffer, type, this.encodingSettings.maxNetworkNBTSize());
    }

    @Override
    public <T> T readTagValue(ByteBuf buffer, NbtType<T> type, long maxReadSize) {
        try (NBTInputStream reader = NbtUtils.createNetworkReader(new ByteBufInputStream(buffer), maxReadSize)) {
            return reader.readValue(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeTagValue(ByteBuf buffer, Object tag) {
        try (NBTOutputStream writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer))) {
            writer.writeValue(tag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readInventoryTransactions(ByteBuf buffer, InventoryTransaction actions) {
        this.readArray(buffer, actions.getActions(), this::readInventoryAction, this.encodingSettings.maxInventoryActionsOrRequests());
    }

    @Override
    public void writeInventoryTransactions(ByteBuf buffer, InventoryTransaction actions) {
        this.writeArray(buffer, actions.getActions(), this::writeInventoryAction);
    }

    protected InventorySource readInventorySource(ByteBuf buffer) {
        final InventorySourceType type = InventorySourceType.from(VarInts.readUnsignedInt(buffer));
        final InventorySource source = new InventorySource();
        source.setSourceType(type);

        switch (type) {
            case CONTAINER_INVENTORY:
            case NON_IMPLEMENTED_FEATURE_TODO:
                source.setContainerID(VarInts.readInt(buffer));
                source.setBitFlags(InventorySourceFlags.NO_FLAG);
                break;
            case GLOBAL_INVENTORY:
            case CREATIVE_INVENTORY:
                source.setContainerID(ContainerId.NONE);
                source.setBitFlags(InventorySourceFlags.NO_FLAG);
                break;
            case WORLD_INTERACTION:
                source.setContainerID(ContainerId.NONE);
                source.setBitFlags(InventorySourceFlags.from(VarInts.readUnsignedInt(buffer)));
                break;
        }
        return source;
    }

    protected void writeInventorySource(ByteBuf buffer, InventorySource inventorySource) {
        requireNonNull(inventorySource, "InventorySource was null");

        VarInts.writeUnsignedInt(buffer, inventorySource.getSourceType().ordinal());

        switch (inventorySource.getSourceType()) {
            case CONTAINER_INVENTORY:
            case NON_IMPLEMENTED_FEATURE_TODO:
                VarInts.writeInt(buffer, inventorySource.getContainerID());
                break;
            case WORLD_INTERACTION:
                VarInts.writeUnsignedInt(buffer, inventorySource.getBitFlags().ordinal());
                break;
        }
    }

    protected void writeInventoryAction(ByteBuf buffer, InventoryAction action) {
        this.writeInventorySource(buffer, action.getSource());
        VarInts.writeUnsignedInt(buffer, action.getSlot());
        this.writeItem(buffer, action.getFromItem());
        this.writeItem(buffer, action.getToItem());
    }

    protected InventoryAction readInventoryAction(ByteBuf buffer) {
        final InventoryAction action = new InventoryAction();
        action.setSource(this.readInventorySource(buffer));
        action.setSlot(VarInts.readUnsignedInt(buffer));
        action.setFromItem(this.readItem(buffer));
        action.setToItem(this.readItem(buffer));
        return action;
    }

    public ItemStackRequest readItemStackRequest(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeStructureSettings(ByteBuf buffer, StructureSettings settings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Skin readSkin(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeSkin(ByteBuf buffer, Skin skin) {
        throw new UnsupportedOperationException();
    }

    // Internal methods

    public AnimationData readAnimationData(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    protected void writeAnimationData(ByteBuf buffer, AnimationData animation) {
        throw new UnsupportedOperationException();
    }

    protected ImageData readImage(ByteBuf buffer) {
        return this.readImage(buffer);
    }

    protected ImageData readImage(ByteBuf buffer, int maxSize) {
        throw new UnsupportedOperationException();
    }

    protected void writeImage(ByteBuf buffer, ImageData image) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void readEntityProperties(ByteBuf buffer, PropertySyncData properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeEntityProperties(ByteBuf buffer, PropertySyncData properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RecipeIngredient readIngredient(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeIngredient(ByteBuf buffer, RecipeIngredient ingredient) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ContainerEnumName readContainerEnumName(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeContainerEnumName(ByteBuf buffer, ContainerEnumName slotType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void readSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DefinitionRegistry<NamedDefinition> getCameraPresetDefinitions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCameraPresetDefinitions(DefinitionRegistry<NamedDefinition> registry) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeItemStackResponseContainer(ByteBuf buffer, ItemStackResponseContainerInfo container) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemStackResponseContainerInfo readItemStackResponseContainer(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FullContainerName readFullContainerName(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeFullContainerName(ByteBuf buffer, FullContainerName containerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Enum<?>> void readLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Enum<?>> void writeLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeDataStoreUpdate(ByteBuf buffer, DataStoreUpdate update) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataStoreUpdate readDataStoreUpdate(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNetworkItemStackDescriptor(ByteBuf buffer, ItemData item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemData readNetworkItemStackDescriptor(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeServerSoundHandle(ByteBuf buffer, ServerSoundHandle serverSoundHandle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ServerSoundHandle readServerSoundHandle(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeGatheringsConfigurationJoinInfo(ByteBuf buffer, GatheringsConfigurationJoinInfo config) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GatheringsConfigurationJoinInfo readGatheringsConfigurationJoinInfo(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writePresenceConfiguration(ByteBuf buffer, PresenceConfiguration configuration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PresenceConfiguration readPresenceConfiguration(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNetworkItemInstanceDescriptor(ByteBuf buffer, ItemData item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemData readNetworkItemInstanceDescriptor(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRedactableString(ByteBuf buffer, RedactableString string) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RedactableString readRedactableString(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeExperiments(ByteBuf buffer, Experiments experiments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Experiments readExperiments(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeExperimentToggle(ByteBuf buffer, ExperimentToggle toggle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ExperimentToggle readExperimentToggle(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeItemUseInventoryTransaction(ByteBuf buffer, ItemUseInventoryTransaction transaction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeSerializedSkin(ByteBuf buffer, SerializedSkin serializedSkin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SerializedSkin readSerializedSkin(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }
}
