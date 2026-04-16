package org.cloudburstmc.protocol.bedrock.codec.v975;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v944.Bedrock_v944;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
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
            .build();

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v944.ACTOR_EVENTS.toBuilder()
            .insert(81, ActorEvent.HURT_WITHOUT_RECEIVING_DAMAGE)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v944.SOUND_EVENTS.toBuilder()
            .replace(599, SoundEvent.PUSHED_BY_PLAYER)
            .insert(600, SoundEvent.BOUNCE)
            .insert(601, SoundEvent.UNDEFINED)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v944.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .update(ActorDataTypes.HEARTBEAT_SOUND_EVENT, new TypeMapTransformer<>(SOUND_EVENTS))
            .build();

    public static final BedrockCodec CODEC = Bedrock_v944.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(975)
            .minecraftVersion("1.26.20")
            .helper(() -> new BedrockCodecHelper_v975(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v975(ACTOR_EVENTS))
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v975.INSTANCE)
            .updateSerializer(ClientMovementPredictionSyncPacket.class, ClientMovementPredictionSyncSerializer_v975.INSTANCE)
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