package org.cloudburstmc.protocol.bedrock.codec.v2168;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v975.BedrockCodecHelper_v975;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.connection.GatheringsConfig;
import org.cloudburstmc.protocol.bedrock.data.connection.PresenceConfig;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRecipeAutoAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftRepairAndDisenchantAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftResultsDeprecatedAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestMineBlockAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.cloudburstmc.protocol.bedrock.data.structure.AnimationMode;
import org.cloudburstmc.protocol.bedrock.data.structure.Mirror;
import org.cloudburstmc.protocol.bedrock.data.structure.Rotation;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureSettings;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.transformer.EntityDataTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.common.util.stream.LittleEndianByteBufInputStream;
import org.cloudburstmc.protocol.common.util.stream.LittleEndianByteBufOutputStream;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

public class BedrockCodecHelper_v2168 extends BedrockCodecHelper_v975 {

    protected final TypeMap<ItemStackRequestActionType> legacyItemStackRequestActionTypeMap = TypeMap.builder(ItemStackRequestActionType.class)
            .insert(0, ItemStackRequestActionType.TAKE)
            .insert(1, ItemStackRequestActionType.PLACE)
            .insert(2, ItemStackRequestActionType.SWAP)
            .insert(3, ItemStackRequestActionType.DROP)
            .insert(4, ItemStackRequestActionType.DESTROY)
            .insert(5, ItemStackRequestActionType.CONSUME)
            .insert(6, ItemStackRequestActionType.CREATE)
            .insert(7, ItemStackRequestActionType.PLACE_IN_ITEM_CONTAINER)
            .insert(8, ItemStackRequestActionType.TAKE_FROM_ITEM_CONTAINER)
            .insert(9, ItemStackRequestActionType.SCREEN_LAB_TABLE_COMBINE)
            .insert(10, ItemStackRequestActionType.SCREEN_BEACON_PAYMENT)
            .insert(11, ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK)
            .insert(12, ItemStackRequestActionType.CRAFT_RECIPE)
            .insert(13, ItemStackRequestActionType.CRAFT_RECIPE_AUTO)
            .insert(14, ItemStackRequestActionType.CRAFT_CREATIVE)
            .insert(15, ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL)
            .insert(16, ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT)
            .insert(17, ItemStackRequestActionType.CRAFT_LOOM)
            .insert(18, ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED)
            .insert(19, ItemStackRequestActionType.CRAFT_RESULTS)
            .build();

    public BedrockCodecHelper_v2168(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                    TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
        this.itemStackRequestActionsVariant = this.itemStackRequestActionsVariant.toBuilder(
                        this.stackRequestActionTypes::getId,
                        (buffer, helper, owner, value) -> VarInts.writeUnsignedInt(buffer, value),
                        (buffer, helper, owner) -> VarInts.readUnsignedInt(buffer)
                )
                .prefix(
                        (buffer, helper, owner, value) ->
                                buffer.writeByte(this.legacyItemStackRequestActionTypeMap.getId(this.stackRequestActionTypes.getType(value))),
                        (buffer, helper, owner) ->
                                (int) buffer.readUnsignedByte()
                )
                .build();
    }

    @Override
    public void readEntityData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "entityDataMap");

        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(this.encodingSettings.maxListSize() <= 0 || length <= this.encodingSettings.maxListSize(), "Entity data size is too big: %s", length);

        for (int i = 0; i < length; i++) {
            int id = VarInts.readUnsignedInt(buffer);
            int oneOf = VarInts.readUnsignedInt(buffer);
            int type = buffer.readUnsignedByte();
            if (oneOf != type) {
                throw new IllegalArgumentException(oneOf + "!=" + type);
            }

            ActorDataFormat format = ActorDataFormat.values()[type];

            Object value;
            switch (format) {
                case BYTE:
                    value = buffer.readByte();
                    break;
                case SHORT:
                    value = buffer.readShortLE();
                    break;
                case INT:
                    value = VarInts.readInt(buffer);
                    break;
                case FLOAT:
                    value = buffer.readFloatLE();
                    break;
                case STRING:
                    value = readString(buffer);
                    break;
                case NBT:
                    value = this.readTag(buffer, Object.class);
                    break;
                case VECTOR3I:
                    value = readVector3i(buffer);
                    break;
                case LONG:
                    value = VarInts.readLong(buffer);
                    break;
                case VECTOR3F:
                    value = readVector3f(buffer);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown entity data type received");
            }

            ActorDataTypeMap.Definition<?>[] definitions = this.entityData.fromId(id, format);
            if (definitions != null) {
                for (ActorDataTypeMap.Definition<?> definition : definitions) {
                    //noinspection unchecked
                    EntityDataTransformer<Object, ?> transformer = (EntityDataTransformer<Object, ?>) definition.getTransformer();
                    Object transformedValue = transformer.deserialize(this, actorDataMap, value);
                    if (transformedValue != null) {
                        actorDataMap.put(definition.getType(), transformedValue);
                    }
                }
            } else {
                log.debug("Unknown entity data: {} type {} value {}", id, format, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void writeEntityData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "entityDataMap");

        // Collect serialized entries first
        List<Map.Entry<ActorDataTypeMap.Definition<?>, Object>> serializedEntries = new LinkedList<>();

        for (Map.Entry<ActorDataType<?>, Object> entry : actorDataMap.entrySet()) {
            ActorDataTypeMap.Definition<?> definition = this.entityData.fromType(entry.getKey());

            try {
                Object value = ((EntityDataTransformer<?, Object>) definition.getTransformer())
                        .serialize(this, actorDataMap, entry.getValue());

                // Skip if transformer returns null (indicating this entry shouldn't be serialized)
                if (value == null) {
                    continue;
                }

                serializedEntries.add(new AbstractMap.SimpleEntry<>(definition, value));
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to encode EntityData " + definition.getId() + " of " + definition.getType().getTypeName(), e);
            }
        }

        VarInts.writeUnsignedInt(buffer, serializedEntries.size());

        for (Map.Entry<ActorDataTypeMap.Definition<?>, Object> entry : serializedEntries) {
            ActorDataTypeMap.Definition<?> definition = entry.getKey();
            Object value = entry.getValue();

            VarInts.writeUnsignedInt(buffer, definition.getId());
            VarInts.writeUnsignedInt(buffer, definition.getFormat().ordinal());
            buffer.writeByte(definition.getFormat().ordinal());

            switch (definition.getFormat()) {
                case BYTE:
                    buffer.writeByte((byte) value);
                    break;
                case SHORT:
                    buffer.writeShortLE((short) value);
                    break;
                case INT:
                    VarInts.writeInt(buffer, (int) value);
                    break;
                case FLOAT:
                    buffer.writeFloatLE((float) value);
                    break;
                case STRING:
                    writeString(buffer, (String) value);
                    break;
                case NBT:
                    this.writeTag(buffer, value);
                    break;
                case VECTOR3I:
                    writeVector3i(buffer, (Vector3i) value);
                    break;
                case LONG:
                    VarInts.writeLong(buffer, (long) value);
                    break;
                case VECTOR3F:
                    writeVector3f(buffer, (Vector3f) value);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown entity data type " + definition.getFormat());
            }
        }
    }

    @Override
    public ItemData readItemInstance(ByteBuf buffer) { // NetworkItemInstanceDescriptorData
        int runtimeId = VarInts.readInt(buffer);

        ItemDefinition definition = runtimeId == 0 ? ItemDefinition.AIR : this.itemDefinitions.getDefinition(runtimeId);
        if (definition == null && log.isDebugEnabled()) {
            log.debug("No ItemDefinition for runtimeId {}, did proxy not set itemDefinitions?", runtimeId);
        }

        int count = buffer.readUnsignedShortLE();
        int aux = VarInts.readUnsignedInt(buffer);

        int blockRuntimeId = VarInts.readInt(buffer);

        NbtMap compoundTag = null;
        long blockingTicks = 0;
        String[] canPlace = new String[0];
        String[] canBreak = new String[0];

        ByteBuf buf = buffer.readSlice(VarInts.readUnsignedInt(buffer));

        if (buf.isReadable()) {
            try (LittleEndianByteBufInputStream stream = new LittleEndianByteBufInputStream(buf);
                 NBTInputStream nbtStream = new NBTInputStream(stream, this.encodingSettings.maxItemNBTSize())) {
                int nbtSize = stream.readShort();

                if (nbtSize > 0) {
                    compoundTag = (NbtMap) nbtStream.readTag();
                } else if (nbtSize == -1) {
                    int tagCount = stream.readUnsignedByte();
                    if (tagCount != 1) throw new IllegalArgumentException("Expected 1 tag but got " + tagCount);
                    compoundTag = (NbtMap) nbtStream.readTag();
                }

                int maxLength = this.encodingSettings.maxListSize();
                int length = stream.readInt();
                checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can place entries, but maximum is %s", length, maxLength);
                canPlace = new String[length];
                for (int i = 0; i < canPlace.length; i++) {
                    canPlace[i] = stream.readUTFMaxLen(this.encodingSettings.maxItemStackTagLength());
                }

                length = stream.readInt();
                checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can break entries, but maximum is %s", length, maxLength);
                canBreak = new String[length];
                for (int i = 0; i < canBreak.length; i++) {
                    canBreak[i] = stream.readUTFMaxLen(this.encodingSettings.maxItemStackTagLength());
                }

                if (definition != null && BLOCKING_ID.equals(definition.getIdentifier())) {
                    blockingTicks = stream.readLong();
                }
            } catch (IOException e) {
                throw new IllegalStateException("Unable to read item user data", e);
            }
        }

        if (buf.isReadable()) {
            log.info("Item user data has {} readable bytes left", buf.readableBytes());

            if (log.isDebugEnabled()) {
                log.debug("Item data:\n{}", ByteBufUtil.prettyHexDump(buf.readerIndex(0)));
            }
        }

        return ItemData.builder()
                .definition(definition)
                .damage(aux)
                .count(count)
                .tag(compoundTag)
                .canPlace(canPlace)
                .canBreak(canBreak)
                .blockingTicks(blockingTicks)
                .blockDefinition(runtimeId == 0 ? ItemData.AIR.getBlockDefinition() : this.blockDefinitions.getDefinition(blockRuntimeId))
                .build();
    }

    @Override
    public ItemData readNetworkItemStackDescriptor(ByteBuf buffer) { // cerealizer_NetworkItemStackDescriptor___SerializedData
        int runtimeId = buffer.readShortLE();

        ItemDefinition definition = runtimeId == 0 ? ItemDefinition.AIR : this.itemDefinitions.getDefinition(runtimeId);
        if (definition == null && log.isDebugEnabled()) {
            log.debug("No ItemDefinition for runtimeId {}, did proxy not set itemDefinitions?", runtimeId);
        }

        int count = buffer.readUnsignedShortLE();
        int aux = VarInts.readUnsignedInt(buffer);

        int netId = 0;
        boolean hasNetId = buffer.readBoolean();

        if (hasNetId) {
            netId = VarInts.readInt(buffer);
        }

        int blockRuntimeId = VarInts.readUnsignedInt(buffer);

        NbtMap compoundTag = null;
        long blockingTicks = 0;
        String[] canPlace = new String[0];
        String[] canBreak = new String[0];

        ByteBuf buf = buffer.readSlice(VarInts.readUnsignedInt(buffer));

        if (buf.isReadable()) {
            try (LittleEndianByteBufInputStream stream = new LittleEndianByteBufInputStream(buf);
                 NBTInputStream nbtStream = new NBTInputStream(stream, this.encodingSettings.maxItemNBTSize())) {
                int nbtSize = stream.readShort();

                if (nbtSize > 0) {
                    compoundTag = (NbtMap) nbtStream.readTag();
                } else if (nbtSize == -1) {
                    int tagCount = stream.readUnsignedByte();
                    if (tagCount != 1) throw new IllegalArgumentException("Expected 1 tag but got " + tagCount);
                    compoundTag = (NbtMap) nbtStream.readTag();
                }

                int maxLength = this.encodingSettings.maxListSize();
                int length = stream.readInt();
                checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can place entries, but maximum is %s", length, maxLength);
                canPlace = new String[length];
                for (int i = 0; i < canPlace.length; i++) {
                    canPlace[i] = stream.readUTFMaxLen(this.encodingSettings.maxItemStackTagLength());
                }

                length = stream.readInt();
                checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can break entries, but maximum is %s", length, maxLength);
                canBreak = new String[length];
                for (int i = 0; i < canBreak.length; i++) {
                    canBreak[i] = stream.readUTFMaxLen(this.encodingSettings.maxItemStackTagLength());
                }

                if (definition != null && BLOCKING_ID.equals(definition.getIdentifier())) {
                    blockingTicks = stream.readLong();
                }
            } catch (IOException e) {
                throw new IllegalStateException("Unable to read item user data", e);
            }
        }

        if (buf.isReadable()) {
            log.info("Item user data has {} readable bytes left", buf.readableBytes());

            if (log.isDebugEnabled()) {
                log.debug("Item data:\n{}", ByteBufUtil.prettyHexDump(buf.readerIndex(0)));
            }
        }

        return ItemData.builder()
                .definition(definition)
                .damage(aux)
                .count(count)
                .tag(compoundTag)
                .canPlace(canPlace)
                .canBreak(canBreak)
                .blockingTicks(blockingTicks)
                .blockDefinition(runtimeId == 0 ? ItemData.AIR.getBlockDefinition() : this.blockDefinitions.getDefinition(blockRuntimeId))
                .usingNetId(hasNetId)
                .netId(netId)
                .build();
    }

    @Override
    public void writeItemInstance(ByteBuf buffer, ItemData item) {
        requireNonNull(item, "item is null!");

        ItemDefinition definition = item.getDefinition();
        boolean air = isAir(definition);

        VarInts.writeInt(buffer, air ? 0 : definition.getRuntimeId());
        buffer.writeShortLE(item.getCount());
        VarInts.writeUnsignedInt(buffer, item.getDamage());

        VarInts.writeInt(buffer, air || item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().getRuntimeId());

        if (air) {
            VarInts.writeUnsignedInt(buffer, 0);
        } else {
            ByteBuf userDataBuf = ByteBufAllocator.DEFAULT.ioBuffer();
            try (LittleEndianByteBufOutputStream stream = new LittleEndianByteBufOutputStream(userDataBuf);
                 NBTOutputStream nbtStream = new NBTOutputStream(stream)) {
                if (item.getTag() != null) {
                    stream.writeShort(-1);
                    stream.writeByte(1); // Hardcoded in current version
                    nbtStream.writeTag(item.getTag());
                } else {
                    userDataBuf.writeShortLE(0);
                }

                String[] canPlace = item.getCanPlace();
                stream.writeInt(canPlace.length);
                for (String aCanPlace : canPlace) {
                    stream.writeUTF(aCanPlace);
                }

                String[] canBreak = item.getCanBreak();
                stream.writeInt(canBreak.length);
                for (String aCanBreak : canBreak) {
                    stream.writeUTF(aCanBreak);
                }

                if (BLOCKING_ID.equals(definition.getIdentifier())) {
                    stream.writeLong(item.getBlockingTicks());
                }

                VarInts.writeUnsignedInt(buffer, userDataBuf.readableBytes());
                buffer.writeBytes(userDataBuf);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to write item user data", e);
            } finally {
                userDataBuf.release();
            }
        }
    }

    @Override
    public void writeNetworkItemStackDescriptor(ByteBuf buffer, ItemData item) {
        requireNonNull(item, "item is null!");

        ItemDefinition definition = item.getDefinition();
        boolean air = isAir(definition);

        buffer.writeShortLE(air ? 0 : definition.getRuntimeId());
        buffer.writeShortLE(item.getCount());
        VarInts.writeUnsignedInt(buffer, item.getDamage());

        buffer.writeBoolean(item.isUsingNetId());
        if (item.isUsingNetId()) {
            VarInts.writeInt(buffer, item.getNetId());
        }

        VarInts.writeUnsignedInt(buffer, air || item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().getRuntimeId());

        if (air) {
            VarInts.writeUnsignedInt(buffer, 0);
        } else {
            ByteBuf userDataBuf = ByteBufAllocator.DEFAULT.ioBuffer();
            try (LittleEndianByteBufOutputStream stream = new LittleEndianByteBufOutputStream(userDataBuf);
                 NBTOutputStream nbtStream = new NBTOutputStream(stream)) {
                if (item.getTag() != null) {
                    stream.writeShort(-1);
                    stream.writeByte(1); // Hardcoded in current version
                    nbtStream.writeTag(item.getTag());
                } else {
                    userDataBuf.writeShortLE(0);
                }

                String[] canPlace = item.getCanPlace();
                stream.writeInt(canPlace.length);
                for (String aCanPlace : canPlace) {
                    stream.writeUTF(aCanPlace);
                }

                String[] canBreak = item.getCanBreak();
                stream.writeInt(canBreak.length);
                for (String aCanBreak : canBreak) {
                    stream.writeUTF(aCanBreak);
                }

                if (BLOCKING_ID.equals(definition.getIdentifier())) {
                    stream.writeLong(item.getBlockingTicks());
                }

                VarInts.writeUnsignedInt(buffer, userDataBuf.readableBytes());
                buffer.writeBytes(userDataBuf);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to write item user data", e);
            } finally {
                userDataBuf.release();
            }
        }
    }

    @Override
    protected void writeItemStackRequestSlotInfo(ByteBuf buffer, ItemStackRequestSlotInfo data) {
        this.writeFullContainerName(buffer, data.getFullContainerName());
        buffer.writeByte(data.getSlot());
        buffer.writeIntLE(data.getNetIdVariant()); // varint->int
    }

    @Override
    protected ItemStackRequestSlotInfo readItemStackRequestSlotInfo(ByteBuf buffer) {
        final ItemStackRequestSlotInfo info = new ItemStackRequestSlotInfo();
        info.setFullContainerName(this.readFullContainerName(buffer));
        info.setSlot(buffer.readUnsignedByte());
        info.setNetIdVariant(buffer.readIntLE()); // varint->int
        return info;
    }

    @Override
    protected void writeItemStackRequestMineBlockAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestMineBlockAction action) {
        VarInts.writeInt(buffer, action.getSlot());
        VarInts.writeInt(buffer, action.getPredictedDurability());
        buffer.writeIntLE(action.getNetIdVariant()); // varint->int
    }

    @Override
    protected ItemStackRequestMineBlockAction readItemStackRequestMineBlockAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestMineBlockAction action = new ItemStackRequestMineBlockAction();
        action.setSlot(VarInts.readInt(buffer));
        action.setPredictedDurability(VarInts.readInt(buffer));
        action.setNetIdVariant(buffer.readIntLE()); // varint->int
        return action;
    }

    @Override
    protected void writeItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRecipeAutoAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        buffer.writeByte(action.getNumberOfRequestedCrafts());
        this.writeArray(buffer, action.getIngredients(), this::writeIngredient2);
    }

    @Override
    protected ItemStackRequestCraftRecipeAutoAction readItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRecipeAutoAction action = new ItemStackRequestCraftRecipeAutoAction();
        action.setRecipeNetId(new RecipeNetId(VarInts.readUnsignedInt(buffer)));
        action.setNumberOfRequestedCrafts(buffer.readUnsignedByte());
        this.readArray(buffer, action.getIngredients(), this::readIngredient2);
        return action;
    }

    @Override
    protected void writeItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftRepairAndDisenchantAction action) {
        buffer.writeIntLE(action.getRecipeNetId().getRawId()); // unsigned varint -> int
        buffer.writeByte(action.getNumberOfRequestedCrafts());
        VarInts.writeInt(buffer, action.getRepairCost());
    }

    @Override
    protected ItemStackRequestCraftRepairAndDisenchantAction readItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftRepairAndDisenchantAction action = new ItemStackRequestCraftRepairAndDisenchantAction();
        action.setRecipeNetId(new RecipeNetId(buffer.readIntLE())); // unsigned varint -> int
        action.setNumberOfRequestedCrafts(buffer.readUnsignedByte());
        action.setRepairCost(VarInts.readInt(buffer));
        return action;
    }

    @Override
    protected void writeItemStackRequestCraftResultsDeprecatedAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestCraftResultsDeprecatedAction action) {
        this.writeArray(buffer, action.getCraftResults(), this::writeItemStackRequestNetworkItemInstanceDescriptor);
        buffer.writeByte(action.getNumCrafts());
    }

    @Override
    protected ItemStackRequestCraftResultsDeprecatedAction readItemStackRequestCraftResultsDeprecatedAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestCraftResultsDeprecatedAction action = new ItemStackRequestCraftResultsDeprecatedAction();
        this.readArray(buffer, action.getCraftResults(), this::readItemStackRequestNetworkItemInstanceDescriptor);
        action.setNumCrafts(buffer.readUnsignedByte());
        return action;
    }

    @Override
    public void writeItem(ByteBuf buffer, ItemData item) {
        writeNetworkItemStackDescriptor(buffer, item);
    }

    @Override
    public ItemData readItem(ByteBuf buffer) {
        return readNetworkItemStackDescriptor(buffer);
    }

    @Override
    public SerializedSkin readSkin(ByteBuf buffer) {
        String skinId = this.readString(buffer);
        String playFabId = this.readString(buffer);
        String skinResourcePatch = this.readString(buffer);
        SkinImage skinData = this.readImage(buffer, SkinImage.SKIN_PERSONA_SIZE);

        List<AnimatedImageData> animations = new ObjectArrayList<>();
        this.readArray(buffer, animations, (b, h) -> this.readAnimationData(b));

        SkinImage capeData = this.readImage(buffer, SkinImage.SINGLE_SKIN_SIZE);
        String geometryData = this.readStringMaxLen(buffer, this.encodingSettings.maxGeometryDataSize());
        String geometryDataEngineVersion = this.readString(buffer);
        String animationData = this.readString(buffer);
        String capeId = this.readString(buffer);
        String fullSkinId = this.readString(buffer);

        ArmSizeType armSize = ArmSizeType.from(buffer.readUnsignedByte());
        int skinColor = buffer.readIntLE();

        List<SerializedPersonaPieceHandle> personaPieces = new ObjectArrayList<>();
        this.readArray(buffer, personaPieces, (buf, h) -> this.readPersonaPiece(buf));

        Map<PieceType, TintMapColor> tintColors = new HashMap<>();
        int tintLength = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < tintLength; i++) {
            PieceType pieceType = PieceType.from(this.readString(buffer));
            TintMapColor tintMapColor = new TintMapColor();
            for (int j = 0; j < 4; j++) {
                tintMapColor.getColors().add(buffer.readIntLE());
            }
            tintColors.put(pieceType, tintMapColor);
        }

        boolean premium = buffer.readBoolean();
        boolean persona = buffer.readBoolean();
        boolean capeOnClassic = buffer.readBoolean();
        boolean primaryUser = buffer.readBoolean();
        boolean overridingPlayerAppearance = buffer.readBoolean();

        TrustedSkinFlag trustedSkinFlag = TrustedSkinFlag.from(this.readString(buffer));
        String profileHash = this.readString(buffer);

        return SerializedSkin.builder()
                .ID(skinId)
                .playFabID(playFabId)
                .resourcePatch(skinResourcePatch)
                .imageData(skinData)
                .animatedImageData(animations)
                .capeImageData(capeData)
                .geometryData(geometryData)
                .geometryDataMinEngineVersion(geometryDataEngineVersion)
                .animationData(animationData)
                .capeID(capeId)
                .fullID(fullSkinId)
                .armSize(armSize)
                .skinColor(skinColor)
                .personaPieces(personaPieces)
                .pieceTintColors(tintColors)
                .isPremium(premium)
                .isPersona(persona)
                .isPersonaCapeOnClassicSkin(capeOnClassic)
                .isPrimaryUser(primaryUser)
                .overridesPlayerAppearance(overridingPlayerAppearance)
                .trustedSkinFlag(trustedSkinFlag)
                .profileHash(profileHash)
                .build();
    }

    @Override
    public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
        requireNonNull(skin, "Skin is null");

        this.writeString(buffer, skin.getID());
        this.writeString(buffer, skin.getPlayFabID());
        this.writeString(buffer, skin.getResourcePatch());
        this.writeImage(buffer, skin.getImageData());

        List<AnimatedImageData> animations = skin.getAnimatedImageData();
        VarInts.writeUnsignedInt(buffer, animations.size());
        for (AnimatedImageData animation : animations) {
            this.writeAnimationData(buffer, animation);
        }

        this.writeImage(buffer, skin.getCapeImageData());
        this.writeString(buffer, skin.getGeometryData());
        this.writeString(buffer, skin.getGeometryDataMinEngineVersion());
        this.writeString(buffer, skin.getAnimationData());
        this.writeString(buffer, skin.getCapeID());
        this.writeString(buffer, skin.getFullID());

        buffer.writeByte(skin.getArmSize().ordinal());
        buffer.writeIntLE(skin.getSkinColor());

        List<SerializedPersonaPieceHandle> pieces = skin.getPersonaPieces();
        VarInts.writeUnsignedInt(buffer, pieces.size());
        for (SerializedPersonaPieceHandle piece : pieces) {
            this.writePersonaPiece(buffer, piece);
        }

        Map<PieceType, TintMapColor> tints = skin.getPieceTintColors();
        VarInts.writeUnsignedInt(buffer, tints.size());
        for (Map.Entry<PieceType, TintMapColor> entry : tints.entrySet()) {
            this.writeString(buffer, entry.getKey().getId());
            List<Integer> colors = entry.getValue().getColors();
            if (colors.size() != 4) {
                throw new IllegalArgumentException("Expected 4 colors in TintMapColor");
            }
            for (int color : colors) {
                buffer.writeIntLE(color);
            }
        }

        buffer.writeBoolean(skin.isPremium());
        buffer.writeBoolean(skin.isPersona());
        buffer.writeBoolean(skin.isPersonaCapeOnClassicSkin());
        buffer.writeBoolean(skin.isPrimaryUser());

        buffer.writeBoolean(skin.isOverridesPlayerAppearance());

        this.writeString(buffer, skin.getTrustedSkinFlag().getId());
        this.writeString(buffer, skin.getProfileHash());
    }

    @Override
    public AnimatedImageData readAnimationData(ByteBuf buffer) {
        SkinImage image = this.readImage(buffer, SkinImage.ANIMATION_SIZE);
        PersonaAnimatedTextureType textureType = TEXTURE_TYPES[VarInts.readUnsignedInt(buffer)];
        float frames = buffer.readFloatLE();
        PersonaAnimationExpression expressionType = EXPRESSION_TYPES[VarInts.readUnsignedInt(buffer)];
        return new AnimatedImageData(image, textureType, frames, expressionType);
    }

    @Override
    public void writeAnimationData(ByteBuf buffer, AnimatedImageData animation) {
        this.writeImage(buffer, animation.getSkinImage());
        VarInts.writeUnsignedInt(buffer, animation.getAnimatedTextureType().ordinal());
        buffer.writeFloatLE(animation.getFrames());
        VarInts.writeUnsignedInt(buffer, animation.getAnimationExpression().ordinal());
    }

    @Override
    public RecipeIngredient readIngredient(ByteBuf buffer) {
        ItemDescriptorType type = DESCRIPTOR_TYPES[VarInts.readUnsignedInt(buffer)];
        ItemDescriptor descriptor = this.readItemDescriptor(buffer, type);
        int count = VarInts.readInt(buffer);
        return new RecipeIngredient(descriptor, count);
    }

    protected RecipeIngredient readIngredient2(ByteBuf buffer) {
        ItemDescriptorType type = DESCRIPTOR_TYPES[VarInts.readUnsignedInt(buffer)];

        int type2 = buffer.readUnsignedByte();
        //type = DESCRIPTOR_TYPES[type2];

        ItemDescriptor descriptor;
        switch (type) {
            case EMPTY:
                descriptor = InvalidDescriptor.INSTANCE;
                break;
            case NAME:
                String id = this.readString(buffer);
                int aux = VarInts.readInt(buffer);
                ItemDefinition definition = this.itemDefinitions.getDefinition(id);
                if (definition == null && log.isDebugEnabled()) {
                    log.debug("No ItemDefinition for id {}, did proxy not set itemDefinitions?", id);
                }
                descriptor = new DefaultDescriptor(definition, aux);
                break;
            case MOLANG:
                descriptor = new MolangDescriptor(this.readString(buffer), buffer.readShortLE());
                break;
            case ITEM_TAG:
                descriptor = new ItemTagDescriptor(this.readString(buffer));
                break;
            default:
                throw new UnsupportedOperationException("ItemDescriptorType");
        }

        int count = buffer.readUnsignedShortLE();
        return new RecipeIngredient(descriptor, count);
    }

    @Override
    public void writeIngredient(ByteBuf buffer, RecipeIngredient ingredient) {
        VarInts.writeUnsignedInt(buffer, Math.min(ingredient.getDescriptor().getType().ordinal(), 1));
        this.writeItemDescriptor(buffer, ingredient.getDescriptor());
        VarInts.writeInt(buffer, ingredient.getStackSize());
    }

    protected void writeIngredient2(ByteBuf buffer, RecipeIngredient ingredient) {
        VarInts.writeUnsignedInt(buffer, ingredient.getDescriptor().getType().ordinal());

        buffer.writeByte(ingredient.getDescriptor().getType().ordinal());

        switch (ingredient.getDescriptor().getType()) {
            case EMPTY:
                break;
            case NAME:
                DefaultDescriptor defaultDescriptor = (DefaultDescriptor) ingredient.getDescriptor();
                this.writeString(buffer, defaultDescriptor.getItemId().getIdentifier());
                VarInts.writeInt(buffer, defaultDescriptor.getAuxValue());
                break;
            case MOLANG:
                MolangDescriptor molangDescriptor = (MolangDescriptor) ingredient.getDescriptor();
                this.writeString(buffer, molangDescriptor.getTagExpression());
                buffer.writeShortLE(molangDescriptor.getMolangVersion());
                break;
            case ITEM_TAG:
                ItemTagDescriptor tagDescriptor = (ItemTagDescriptor) ingredient.getDescriptor();
                this.writeString(buffer, tagDescriptor.getItemTag());
                break;
            default:
                throw new UnsupportedOperationException("ItemDescriptorType");
        }

        buffer.writeShortLE(ingredient.getStackSize());
    }

    @Override
    protected ItemDescriptor readItemDescriptor(ByteBuf buffer, ItemDescriptorType type) {
        ItemDescriptor descriptor;
        if (type != ItemDescriptorType.EMPTY) {
            String desc = this.readString(buffer);
            type = ItemDescriptorType.fromName(desc);
        }

        switch (type) {
            case EMPTY:
                int aux_ = VarInts.readInt(buffer);
                descriptor = InvalidDescriptor.INSTANCE;
                break;
            case NAME:
                String id = this.readString(buffer);
                int aux = VarInts.readInt(buffer);
                ItemDefinition definition = this.itemDefinitions.getDefinition(id);
                if (definition == null && log.isDebugEnabled()) {
                    log.debug("No ItemDefinition for id {}, did proxy not set itemDefinitions?", id);
                }
                descriptor = new DefaultDescriptor(definition, aux);
                break;
            case MOLANG:
                descriptor = new MolangDescriptor(this.readString(buffer), buffer.readShortLE());
                break;
            case ITEM_TAG:
                descriptor = new ItemTagDescriptor(this.readString(buffer));
                int aux__ = VarInts.readInt(buffer);
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return descriptor;
    }

    @Override
    protected void writeItemDescriptor(ByteBuf buffer, ItemDescriptor descriptor) {
        if (descriptor.getType() != ItemDescriptorType.EMPTY) {
            this.writeString(buffer, descriptor.getType().getSerializeName());
        }

        switch (descriptor.getType()) {
            case EMPTY:
                VarInts.writeInt(buffer, 32767);
                break;
            case NAME:
                DefaultDescriptor defaultDescriptor = (DefaultDescriptor) descriptor;
                this.writeString(buffer, defaultDescriptor.getItemId().getIdentifier());
                VarInts.writeInt(buffer, defaultDescriptor.getAuxValue());
                break;
            case MOLANG:
                MolangDescriptor molangDescriptor = (MolangDescriptor) descriptor;
                this.writeString(buffer, molangDescriptor.getTagExpression());
                buffer.writeShortLE(molangDescriptor.getMolangVersion());
                break;
            case ITEM_TAG:
                ItemTagDescriptor tagDescriptor = (ItemTagDescriptor) descriptor;
                this.writeString(buffer, tagDescriptor.getItemTag());
                VarInts.writeInt(buffer, 32767);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        String paletteName = this.readString(buffer);
        boolean ignoringEntities = buffer.readBoolean();
        boolean ignoringBlocks = buffer.readBoolean();
        boolean nonTickingPlayersAndTickingAreasEnabled = buffer.readBoolean();
        Vector3i size = this.readBlockPosition(buffer);
        Vector3i offset = this.readBlockPosition(buffer);
        long lastEditedByEntityId = VarInts.readLong(buffer);
        Rotation rotation = Rotation.from(buffer.readUnsignedByte());
        Mirror mirror = Mirror.from(buffer.readUnsignedByte());
        AnimationMode animationMode = AnimationMode.from(buffer.readUnsignedByte());
        float animationSeconds = buffer.readFloatLE();
        float integrityValue = buffer.readFloatLE();
        int integritySeed = buffer.readIntLE();
        Vector3f pivot = this.readVector3f(buffer);

        return new StructureSettings(paletteName, ignoringEntities, ignoringBlocks,
                nonTickingPlayersAndTickingAreasEnabled, size, offset, lastEditedByEntityId, rotation, mirror,
                animationMode, animationSeconds, integrityValue, integritySeed, pivot);
    }

    @Override
    protected ItemStackResponseSlotInfo readItemStackResponseSlotInfo(ByteBuf buffer) {
        int requestedSlot = buffer.readUnsignedByte();
        int slot = buffer.readUnsignedByte();
        int amount = buffer.readUnsignedByte();
        int stackNetworkId = buffer.readBoolean() && buffer.readBoolean() ? VarInts.readInt(buffer) : 0;
        String customName = this.readString(buffer);
        String filteredCustomName = this.readOptional(buffer, null, this::readString);
        int durabilityCorrection = VarInts.readInt(buffer);
        return new ItemStackResponseSlotInfo(requestedSlot, slot, amount, new ItemStackNetId(stackNetworkId),
                new RedactableString(customName, filteredCustomName), durabilityCorrection);

    }

    @Override
    protected void writeItemStackResponseSlotInfo(ByteBuf buffer, ItemStackResponseSlotInfo info) {
        buffer.writeByte(info.getRequestedSlot());
        buffer.writeByte(info.getSlot());
        buffer.writeByte(info.getAmount());
        buffer.writeBoolean(true);
        this.writeOptional(buffer, id -> id > 0, info.getItemStackNetId().getID(), VarInts::writeInt);
        this.writeString(buffer, info.getCustomName().getUnredacted());
        this.writeOptionalNull(buffer, info.getCustomName().getRedacted(), this::writeString);
        VarInts.writeInt(buffer, info.getDurabilityCorrection());
    }

    private ItemData readItemStackRequestNetworkItemInstanceDescriptor(ByteBuf buffer) {
        ItemDescriptorType type = DESCRIPTOR_TYPES[VarInts.readUnsignedInt(buffer)];

        int typeStr = buffer.readUnsignedByte();

        ItemDescriptor descriptor = InvalidDescriptor.INSTANCE;
        if (type != ItemDescriptorType.EMPTY) {
            String id = this.readString(buffer);

            int aux = VarInts.readInt(buffer);
            ItemDefinition definition = this.itemDefinitions.getDefinition(id);
            if (definition == null && log.isDebugEnabled()) {
                log.debug("No ItemDefinition for id {}, did proxy not set itemDefinitions?", id);
            }
            descriptor = new DefaultDescriptor(definition, aux);
        }

        ItemDefinition definition = descriptor == InvalidDescriptor.INSTANCE ? ItemData.AIR.getDefinition() : ((DefaultDescriptor) descriptor).getItemId();
        int aux = descriptor == InvalidDescriptor.INSTANCE ? 0 : ((DefaultDescriptor) descriptor).getAuxValue();

        int count = buffer.readShortLE();

        int blockRuntimeId = VarInts.readUnsignedInt(buffer);

        NbtMap compoundTag = null;
        long blockingTicks = 0;
        String[] canPlace = new String[0];
        String[] canBreak = new String[0];

        ByteBuf buf = buffer.readSlice(VarInts.readUnsignedInt(buffer));

        if (buf.isReadable()) {
            try (LittleEndianByteBufInputStream stream = new LittleEndianByteBufInputStream(buf);
                 NBTInputStream nbtStream = new NBTInputStream(stream, this.encodingSettings.maxItemNBTSize())) {
                int nbtSize = stream.readShort();

                if (nbtSize > 0) {
                    compoundTag = (NbtMap) nbtStream.readTag();
                } else if (nbtSize == -1) {
                    int tagCount = stream.readUnsignedByte();
                    if (tagCount != 1) throw new IllegalArgumentException("Expected 1 tag but got " + tagCount);
                    compoundTag = (NbtMap) nbtStream.readTag();
                }

                int maxLength = this.encodingSettings.maxListSize();
                int length = stream.readInt();
                checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can place entries, but maximum is %s", length, maxLength);
                canPlace = new String[length];
                for (int i = 0; i < canPlace.length; i++) {
                    canPlace[i] = stream.readUTFMaxLen(this.encodingSettings.maxItemStackTagLength());
                }

                length = stream.readInt();
                checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can break entries, but maximum is %s", length, maxLength);
                canBreak = new String[length];
                for (int i = 0; i < canBreak.length; i++) {
                    canBreak[i] = stream.readUTFMaxLen(this.encodingSettings.maxItemStackTagLength());
                }

                if (definition != null && BLOCKING_ID.equals(definition.getIdentifier())) {
                    blockingTicks = stream.readLong();
                }
            } catch (IOException e) {
                throw new IllegalStateException("Unable to read item user data", e);
            }
        }

        if (buf.isReadable()) {
            log.info("Item user data has {} readable bytes left", buf.readableBytes());

            if (log.isDebugEnabled()) {
                log.debug("Item data:\n{}", ByteBufUtil.prettyHexDump(buf.readerIndex(0)));
            }
        }

        return ItemData.builder()
                .definition(definition)
                .damage(aux)
                .count(count)
                .tag(compoundTag)
                .canPlace(canPlace)
                .canBreak(canBreak)
                .blockingTicks(blockingTicks)
                .blockDefinition(definition.getRuntimeId() == 0 ? ItemData.AIR.getBlockDefinition() : this.blockDefinitions.getDefinition(blockRuntimeId))
                .build();
    }

    private void writeItemStackRequestNetworkItemInstanceDescriptor(ByteBuf buffer, ItemData item) {
        requireNonNull(item, "item is null!");

        ItemDefinition definition = item.getDefinition();
        boolean air = isAir(definition);

        VarInts.writeUnsignedInt(buffer, air ? 0 : 1); //descriptor type
        buffer.writeByte(air ? 0 : 1); // type again
        if (!air) {
            this.writeString(buffer, definition.getIdentifier());
            VarInts.writeInt(buffer, item.getDamage());
        }

        buffer.writeShortLE(item.getCount());

        VarInts.writeUnsignedInt(buffer, air || item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().getRuntimeId());

        if (air) {
            VarInts.writeUnsignedInt(buffer, 0);
        } else {
            ByteBuf userDataBuf = ByteBufAllocator.DEFAULT.ioBuffer();
            try (LittleEndianByteBufOutputStream stream = new LittleEndianByteBufOutputStream(userDataBuf);
                 NBTOutputStream nbtStream = new NBTOutputStream(stream)) {
                if (item.getTag() != null) {
                    stream.writeShort(-1);
                    stream.writeByte(1); // Hardcoded in current version
                    nbtStream.writeTag(item.getTag());
                } else {
                    userDataBuf.writeShortLE(0);
                }

                String[] canPlace = item.getCanPlace();
                stream.writeInt(canPlace.length);
                for (String aCanPlace : canPlace) {
                    stream.writeUTF(aCanPlace);
                }

                String[] canBreak = item.getCanBreak();
                stream.writeInt(canBreak.length);
                for (String aCanBreak : canBreak) {
                    stream.writeUTF(aCanBreak);
                }

                if (BLOCKING_ID.equals(definition.getIdentifier())) {
                    stream.writeLong(item.getBlockingTicks());
                }

                VarInts.writeUnsignedInt(buffer, userDataBuf.readableBytes());
                buffer.writeBytes(userDataBuf);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to write item user data", e);
            } finally {
                userDataBuf.release();
            }
        }
    }

    @Override
    public void writePresenceConfig(ByteBuf buffer, PresenceConfig config) {
        this.writeOptionalNull(buffer, config.getRichPresenceId(), this::writeString);
    }

    @Override
    public PresenceConfig readPresenceConfig(ByteBuf buffer) {
        final PresenceConfig config = new PresenceConfig();
        config.setRichPresenceId(this.readOptional(buffer, null, (buf, helper) -> this.readStringMaxLen(buf, 50)));
        return config;
    }

    @Override
    public void writeGatheringsConfig(ByteBuf buffer, GatheringsConfig config) {
        this.writeUuid(buffer, config.getExperienceId());
        this.writeString(buffer, config.getExperienceName());
        this.writeOptionalNull(buffer, config.getWorldId(), this::writeUuid);
        this.writeOptionalNull(buffer, config.getWorldName(), this::writeString);
        this.writeString(buffer, config.getCreatorId());
        this.writeOptionalNull(buffer, config.getTargetId(), this::writeUuid);
        this.writeOptionalNull(buffer, config.getScenarioId(), this::writeString);
        this.writeOptionalNull(buffer, config.getServerId(), this::writeString);
    }

    @Override
    public GatheringsConfig readGatheringsConfig(ByteBuf buffer) {
        final GatheringsConfig config = new GatheringsConfig();
        config.setExperienceId(this.readUuid(buffer));
        config.setExperienceName(this.readString(buffer));
        config.setWorldId(this.readOptional(buffer, null, this::readUuid));
        config.setWorldName(this.readOptional(buffer, null, this::readString));
        config.setCreatorId(this.readString(buffer));
        config.setTargetId(this.readOptional(buffer, null, this::readUuid));
        config.setScenarioId(this.readOptional(buffer, null, this::readString));
        config.setServerId(this.readOptional(buffer, null, this::readString));
        return config;
    }

    @Override
    public void writeRedactableString(ByteBuf buffer, RedactableString string) {
        this.writeString(buffer, string.getUnredacted());
        this.writeOptionalNull(buffer, string.getRedacted(), this::writeString);
    }

    @Override
    public RedactableString readRedactableString(ByteBuf buffer) {
        final RedactableString string = new RedactableString();
        string.setUnredacted(this.readString(buffer));
        string.setRedacted(this.readOptional(buffer, null, this::readString));
        return string;
    }
}