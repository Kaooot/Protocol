package org.cloudburstmc.protocol.bedrock.codec.v975;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v671.Bedrock_v671;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.AvailableCommandsSerializer_v898;
import org.cloudburstmc.protocol.bedrock.codec.v944.Bedrock_v944;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v975 extends Bedrock_v944 {

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v944.ACTOR_FLAGS
            .toBuilder()
            .insert(127, ActorFlags.USES_LEGACY_FRICTION)
            .insert(128, ActorFlags.USES_UNIFORM_AIR_DRAG)
            .insert(129, ActorFlags.NAMEPLATE_DEPTH_TESTED)
            .build();

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v944.ACTOR_EVENTS.toBuilder()
            .insert(81, ActorEvent.HURT_WITHOUT_RECEIVING_DAMAGE)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v944.SOUND_EVENTS.toBuilder()
            .replace(599, SoundEvent.PUSHED_BY_PLAYER)
            .insert(600, SoundEvent.BOUNCE)
            .insert(601, SoundEvent.UNDEFINED)
            .build();

    protected static final TypeMap<ParticleType> PARTICLE_TYPES = Bedrock_v944.PARTICLE_TYPES.toBuilder()
            .insert(101, ParticleType.SULFUR_CUBE)
            .build();

    protected static final TypeMap<LevelEventType> LEVEL_EVENTS = Bedrock_v944.LEVEL_EVENTS.toBuilder()
            .insert(LEVEL_EVENT_PARTICLE_TYPE, PARTICLE_TYPES)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v944.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .insert(ActorDataTypes.RESERVED_139, 139, ActorDataFormat.LONG)
            .insert(ActorDataTypes.NAMEPLATE_RENDER_DISTANCE_MAX, 140, ActorDataFormat.FLOAT)
            .update(ActorDataTypes.HEARTBEAT_SOUND_EVENT, new TypeMapTransformer<>(SOUND_EVENTS))
            .update(ActorDataTypes.DATA_PARTICLE, new TypeMapTransformer<>(PARTICLE_TYPES))
            .build();

    protected static final TypeMap<TextProcessingEventOrigin> TEXT_PROCESSING_ORIGINS = Bedrock_v944.TEXT_PROCESSING_ORIGINS
            .toBuilder()
            .insert(15, TextProcessingEventOrigin.DATA_DRIVEN_UI)
            .build();

    protected static final TypeMap<CommandParam> COMMAND_PARAMS = Bedrock_v671.COMMAND_PARAMS.toBuilder()
            .remove(134217728)
            .shift(86, 1)
            .insert(86, CommandParam.CLOCK_TIME_MARKER_NAME)
            .insert(134217728, CommandParam.CHAINED_COMMAND)
            .build();

    protected static final TypeMap<MemoryCategory> MEMORY_CATEGORY_TYPES = Bedrock_v944.MEMORY_CATEGORY_TYPES.toBuilder()
            .remove(68)
            .shift(60, 68, 1)
            .shift(91, 68, -1)
            .insert(60, MemoryCategory.RENDERING_RENDER_REGISTRY)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v944.CODEC.toBuilder()
            .protocolVersion(975)
            .minecraftVersion("1.26.20")
            .helper(() -> new BedrockCodecHelper_v975(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v975(ACTOR_EVENTS))
            .updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v898(COMMAND_PARAMS))
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v975.INSTANCE)
            .updateSerializer(ClientboundAttributeLayerSyncPacket.class, ClientboundAttributeLayerSyncSerializer_v975.INSTANCE)
            .updateSerializer(ClientMovementPredictionSyncPacket.class, ClientMovementPredictionSyncSerializer_v975.INSTANCE)
            .updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v975.INSTANCE)
            .updateSerializer(DimensionDataPacket.class, DimensionDataSerializer_v975.INSTANCE)
            .updateSerializer(DisconnectPacket.class, DisconnectSerializer_v975.INSTANCE)
            .updateSerializer(InventorySlotPacket.class, InventorySlotSerializer_v975.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v975(SOUND_EVENTS))
            .updateSerializer(LocatorBarPacket.class, LocatorBarSerializer_v975.INSTANCE)
            .updateSerializer(MobEquipmentPacket.class, MobEquipmentSerializer_v975.INSTANCE)
            .updateSerializer(PartyChangedPacket.class, PartyChangedSerializer_v975.INSTANCE)
            .updateSerializer(PlayerEnchantOptionsPacket.class, PlayerEnchantOptionsSerializer_v975.INSTANCE)
            .updateSerializer(PlaySoundPacket.class, PlaySoundSerializer_v975.INSTANCE)
            .updateSerializer(PrimitiveShapesPacket.class, PrimitiveShapesSerializer_v975.INSTANCE)
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v975(MEMORY_CATEGORY_TYPES))
            .updateSerializer(UpdateClientOptionsPacket.class, UpdateClientOptionsSerializer_v975.INSTANCE)
            .registerPacket(ServerStoreInfoPacket::new, ServerStoreInfoSerializer_v975.INSTANCE, 346, PacketRecipient.CLIENT)
            .registerPacket(ServerPresenceInfoPacket::new, ServerPresenceInfoSerializer_v975.INSTANCE, 347, PacketRecipient.CLIENT)
            .build();
}