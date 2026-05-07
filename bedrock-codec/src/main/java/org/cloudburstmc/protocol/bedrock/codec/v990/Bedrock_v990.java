package org.cloudburstmc.protocol.bedrock.codec.v990;

import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v975.Bedrock_v975;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.LevelSoundEventSerializer_v975;
import org.cloudburstmc.protocol.bedrock.codec.v990.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.bedrock.transformer.TypeMapTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class Bedrock_v990 extends Bedrock_v975 {

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v975.SOUND_EVENTS.toBuilder()
            .replace(601, SoundEvent.SLIME_LANDING)
            .insert(602, SoundEvent.ABSORB_BLOCK)
            .insert(603, SoundEvent.EJECT_BLOCK)
            .insert(604, SoundEvent.UNDEFINED)
            .build();

    protected static final ActorDataTypeMap ACTOR_DATA = Bedrock_v975.ACTOR_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ACTOR_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ACTOR_FLAGS, 1))
            .update(ActorDataTypes.HEARTBEAT_SOUND_EVENT, new TypeMapTransformer<>(SOUND_EVENTS))
            .build();

    public static final BedrockCodec CODEC = Bedrock_v975.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(990)
            .minecraftVersion("1.26.30")
            .helper(() -> new BedrockCodecHelper_v990(ACTOR_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v990.INSTANCE)
            .updateSerializer(BossEventPacket.class, BossEventSerializer_v990.INSTANCE)
            .updateSerializer(ClientboundAttributeLayerSyncPacket.class, ClientboundAttributeLayerSyncSerializer_v990.INSTANCE)
            .updateSerializer(InventoryTransactionPacket.class, InventoryTransactionSerializer_v990.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v975(SOUND_EVENTS))
            .updateSerializer(ServerboundDiagnosticsPacket.class, new ServerboundDiagnosticsSerializer_v990(MEMORY_CATEGORY_TYPES))
            .updateSerializer(ServerPresenceInfoPacket.class, ServerPresenceInfoSerializer_v990.INSTANCE)
            .updateSerializer(StartGamePacket.class, StartGameSerializer_v990.INSTANCE)
            .updateSerializer(SubChunkRequestPacket.class, SubChunkRequestSerializer_v990.INSTANCE)
            .registerPacket(ClientboundUpdateSoundDataPacket::new, ClientboundUpdateSoundSerializer_v990.INSTANCE, 348, PacketRecipient.CLIENT)
            .registerPacket(SendPartyDestinationCookiePacket::new, SendPartyDestinationCookieSerializer_v990.INSTANCE, 349, PacketRecipient.SERVER)
            .registerPacket(PartyDestinationCookieResponsePacket::new, PartyDestinationCookieResponseSerializer_v990.INSTANCE, 350, PacketRecipient.CLIENT)
            .build();
}