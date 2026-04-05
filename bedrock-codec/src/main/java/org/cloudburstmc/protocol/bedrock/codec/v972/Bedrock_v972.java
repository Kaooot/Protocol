package org.cloudburstmc.protocol.bedrock.codec.v972;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v859.Bedrock_v859;
import org.cloudburstmc.protocol.bedrock.codec.v944.Bedrock_v944;
import org.cloudburstmc.protocol.bedrock.codec.v972.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v972 extends Bedrock_v944 {

    protected static final TypeMap<ActorFlags> ACTOR_FLAGS = Bedrock_v944.ACTOR_FLAGS
            .toBuilder()
            .insert(127, ActorFlags.USES_LEGACY_FRICTION)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v944.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .build();

    protected static final TypeMap<ActorEvent> ACTOR_EVENTS = Bedrock_v859.ACTOR_EVENTS.toBuilder()
            .insert(81, ActorEvent.HURT_WITHOUT_RECEIVING_DAMAGE)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v944.SOUND_EVENTS.toBuilder()
            .replace(599, SoundEvent.PUSHED_BY_PLAYER)
            .insert(600, SoundEvent.BOUNCE)
            .insert(601, SoundEvent.UNDEFINED)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v944.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(972)
            .minecraftVersion("1.26.20")
            .helper(() -> new BedrockCodecHelper_v972(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(ActorEventPacket.class, new ActorEventSerializer_v972(ACTOR_EVENTS))
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v972.INSTANCE)
            .updateSerializer(ClientMovementPredictionSyncPacket.class, ClientMovementPredictionSyncSerializer_v972.INSTANCE)
            .updateSerializer(DimensionDataPacket.class, DimensionDataSerializer_v972.INSTANCE)
            .updateSerializer(DisconnectPacket.class, DisconnectSerializer_v972.INSTANCE)
            .updateSerializer(InventorySlotPacket.class, InventorySlotSerializer_v972.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v972(SOUND_EVENTS))
            .updateSerializer(LocatorBarPacket.class, LocatorBarSerializer_v972.INSTANCE)
            .updateSerializer(MobEquipmentPacket.class, MobEquipmentSerializer_v972.INSTANCE)
            .updateSerializer(PartyChangedPacket.class, PartyChangedSerializer_v972.INSTANCE)
            .updateSerializer(PlayerEnchantOptionsPacket.class, PlayerEnchantOptionsSerializer_v972.INSTANCE)
            .updateSerializer(PrimitiveShapesPacket.class, PrimitiveShapesSerializer_v972.INSTANCE)
            .updateSerializer(UpdateClientOptionsPacket.class, UpdateClientOptionsSerializer_v972.INSTANCE)
            .registerPacket(ServerStoreInfoPacket::new, ServerStoreInfoSerializer_v972.INSTANCE, 346, PacketRecipient.CLIENT)
            .registerPacket(ServerPresenceInfoPacket::new, ServerPresenceInfoSerializer_v972.INSTANCE, 347, PacketRecipient.CLIENT)
            .build();
}