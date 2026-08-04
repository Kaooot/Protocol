package org.cloudburstmc.protocol.bedrock.codec;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.data.ServerSoundHandle;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.payload.common.GatheringsConfigurationJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
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
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureSettings;
import org.cloudburstmc.protocol.bedrock.data.skin.Skin;
import org.cloudburstmc.protocol.common.DefinitionRegistry;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.function.*;

public interface BedrockCodecHelper {

    void setItemDefinitions(DefinitionRegistry<ItemDefinition> registry);

    void setBlockDefinitions(DefinitionRegistry<BlockDefinition> registry);

    void setCameraPresetDefinitions(DefinitionRegistry<NamedDefinition> registry);

    DefinitionRegistry<ItemDefinition> getItemDefinitions();

    DefinitionRegistry<BlockDefinition> getBlockDefinitions();

    DefinitionRegistry<NamedDefinition> getCameraPresetDefinitions();

    EncodingSettings getEncodingSettings();

    void setEncodingSettings(EncodingSettings settings);

    // Array serialization (with helper)

    <T> void readArray(ByteBuf buffer, Collection<T> array, BiFunction<ByteBuf, BedrockCodecHelper, T> function);

    default <T> void readArray(ByteBuf buffer, Collection<T> array, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength) {
        this.readArray(buffer, array, VarInts::readUnsignedInt, function, maxLength);
    }

    <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, BiFunction<ByteBuf, BedrockCodecHelper, T> function);

    <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength);

    default <T> void writeArray(ByteBuf buffer, Collection<T> array, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
        this.writeArray(buffer, array, VarInts::writeUnsignedInt, consumer);
    }

    <T> void writeArray(ByteBuf buffer, Collection<T> array, ObjIntConsumer<ByteBuf> lengthWriter, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer);

    <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, BedrockCodecHelper, T> function);

    <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength);

    <T> void writeArray(ByteBuf buffer, T[] array, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer);

    // Array serialization (without helper)

    <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function);

    <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, Function<ByteBuf, T> function);

    <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, Function<ByteBuf, T> function, int maxLength);

    <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function, int maxLength);

    <T> void writeArray(ByteBuf buffer, Collection<T> array, BiConsumer<ByteBuf, T> consumer);

    <T> void writeArray(ByteBuf buffer, Collection<T> array, ObjIntConsumer<ByteBuf> lengthWriter, BiConsumer<ByteBuf, T> consumer);

    <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function);

    <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function, int maxLength);

    <T> void writeArray(ByteBuf buffer, T[] array, BiConsumer<ByteBuf, T> consumer);

    // Encoding methods

    ActorLink readActorLink(ByteBuf buffer);

    void writeActorLink(ByteBuf buffer, ActorLink link);

    ItemData readNetItem(ByteBuf buffer);

    void writeNetItem(ByteBuf buffer, ItemData item);

    ItemData readItem(ByteBuf buffer);

    void writeItem(ByteBuf buffer, ItemData item);

    ItemData readItemInstance(ByteBuf buffer);

    void writeItemInstance(ByteBuf buffer, ItemData item);

    CommandOriginData readCommandOrigin(ByteBuf buffer);

    void writeCommandOrigin(ByteBuf buffer, CommandOriginData commandOrigin);

    GameRuleData<?> readGameRule(ByteBuf buffer);

    void writeGameRule(ByteBuf buffer, GameRuleData<?> gameRule);

    void readActorData(ByteBuf buffer, ActorDataMap entityData);

    void writeActorData(ByteBuf buffer, ActorDataMap entityData);

    CommandEnumData readCommandEnum(ByteBuf buffer, boolean soft);

    void writeCommandEnum(ByteBuf buffer, CommandEnumData commandEnum);

    StructureSettings readStructureSettings(ByteBuf buffer);

    void writeStructureSettings(ByteBuf buffer, StructureSettings settings);

    Skin readSkin(ByteBuf buffer);

    void writeSkin(ByteBuf buffer, Skin skin);

    byte[] readByteArray(ByteBuf buffer);

    byte[] readByteArray(ByteBuf buffer, int maxLength);

    void writeByteArray(ByteBuf buffer, byte[] bytes);

    ByteBuf readByteBuf(ByteBuf buffer);

    void writeByteBuf(ByteBuf buffer, ByteBuf toWrite);

    String readString(ByteBuf buffer);

    String readStringMaxLen(ByteBuf buffer, int maxLength);

    void writeString(ByteBuf buffer, String string);

    UUID readUuid(ByteBuf buffer);

    void writeUuid(ByteBuf buffer, UUID uuid);

    Vector3f readVector3f(ByteBuf buffer);

    void writeVector3f(ByteBuf buffer, Vector3f vector3f);

    Vector2f readVector2f(ByteBuf buffer);

    void writeVector2f(ByteBuf buffer, Vector2f vector2f);

    Vector3i readVector3i(ByteBuf buffer);

    void writeVector3i(ByteBuf buffer, Vector3i vector3i);

    float readByteAngle(ByteBuf buffer);

    void writeByteAngle(ByteBuf buffer, float angle);

    Vector3i readBlockPosition(ByteBuf buffer);

    void writeBlockPosition(ByteBuf buffer, Vector3i blockPosition);

    <T> T readTag(ByteBuf buffer, Class<T> expected);

    <T> T readTag(ByteBuf buffer, Class<T> expected, long maxReadSize);

    void writeTag(ByteBuf buffer, Object tag);

    <T> T readTagLE(ByteBuf buffer, Class<T> expected);

    <T> T readTagLE(ByteBuf buffer, Class<T> expected, long maxReadSize);

    void writeTagLE(ByteBuf buffer, Object tag);

    <T> T readTagValue(ByteBuf buffer, NbtType<T> type);

    <T> T readTagValue(ByteBuf buffer, NbtType<T> type, long maxReadSize);

    void writeTagValue(ByteBuf buffer, Object tag);

    void readInventoryTransactions(ByteBuf buffer, InventoryTransaction actions);

    void writeInventoryTransactions(ByteBuf buffer, InventoryTransaction actions);

    ItemStackRequest readItemStackRequest(ByteBuf buffer);

    void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request);

    <O> O readOptional(ByteBuf buffer, O emptyValue, Function<ByteBuf, O> function);

    <T> T readOptional(ByteBuf buffer, T emptyValue, BiFunction<ByteBuf, BedrockCodecHelper, T> function);

    <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, BiConsumer<ByteBuf, T> consumer);

    <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer);

    <T> void writeOptionalNull(ByteBuf buffer, T object, BiConsumer<ByteBuf, T> consumer);

    <T> void writeOptionalNull(ByteBuf buffer, T object, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer);

    void readEntityProperties(ByteBuf buffer, PropertySyncData properties);

    void writeEntityProperties(ByteBuf buffer, PropertySyncData properties);

    RecipeIngredient readIngredient(ByteBuf buffer);

    void writeIngredient(ByteBuf buffer, RecipeIngredient ingredient);

    void writeContainerEnumName(ByteBuf buffer, ContainerEnumName slotType);

    ContainerEnumName readContainerEnumName(ByteBuf buffer);

    void writeSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data);

    void readSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data);

    void writeItemStackResponseContainer(ByteBuf buffer, ItemStackResponseContainerInfo container);

    ItemStackResponseContainerInfo readItemStackResponseContainer(ByteBuf buffer);

    void writeFullContainerName(ByteBuf buffer, FullContainerName containerName);

    FullContainerName readFullContainerName(ByteBuf buffer);

    <T extends Enum<?>> void writeLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz);

    <T extends Enum<?>> void readLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz);

    void writeDataStoreUpdate(ByteBuf buffer, DataStoreUpdate update);

    DataStoreUpdate readDataStoreUpdate(ByteBuf buffer);

    void writeNetworkItemStackDescriptor(ByteBuf buffer, ItemData item);

    ItemData readNetworkItemStackDescriptor(ByteBuf buffer);

    void writeServerSoundHandle(ByteBuf buffer, ServerSoundHandle serverSoundHandle);

    ServerSoundHandle readServerSoundHandle(ByteBuf buffer);

    void writeGatheringsConfigurationJoinInfo(ByteBuf buffer, GatheringsConfigurationJoinInfo config);

    GatheringsConfigurationJoinInfo readGatheringsConfigurationJoinInfo(ByteBuf buffer);

    void writePresenceConfiguration(ByteBuf buffer, PresenceConfiguration config);

    PresenceConfiguration readPresenceConfiguration(ByteBuf buffer);

    void writeNetworkItemInstanceDescriptor(ByteBuf buffer, ItemData item);

    ItemData readNetworkItemInstanceDescriptor(ByteBuf buffer);

    void writeRedactableString(ByteBuf buffer, RedactableString string);

    RedactableString readRedactableString(ByteBuf buffer);

    void writeExperiments(ByteBuf buffer, Experiments experiments);

    Experiments readExperiments(ByteBuf buffer);

    void writeExperimentToggle(ByteBuf buffer, ExperimentToggle toggle);

    ExperimentToggle readExperimentToggle(ByteBuf buffer);

    void writeItemUseInventoryTransaction(ByteBuf buffer, ItemUseInventoryTransaction transaction);

    ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer);

    void writeSerializedSkin(ByteBuf buffer, SerializedSkin serializedSkin);

    SerializedSkin readSerializedSkin(ByteBuf buffer);
}
