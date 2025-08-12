package org.cloudburstmc.protocol.bedrock.codec.v786;


import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v776.BedrockCodecHelper_v776;
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v786 extends Bedrock_v776 {

    protected static final TypeMap<ActorFlag> ENTITY_FLAGS = Bedrock_v776.ENTITY_FLAGS
            .toBuilder()
            .insert(120, ActorFlag.ROTATION_AXIS_ALIGNED)
            .insert(121, ActorFlag.COLLIDABLE)
            .insert(122, ActorFlag.WASD_AIR_CONTROLLED)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v776.ENTITY_DATA
            .toBuilder()
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1))
            .build();

    public static final BedrockCodec CODEC = Bedrock_v776.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(786)
            .minecraftVersion("1.21.70")
            .helper(() -> new BedrockCodecHelper_v776(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(ClientMovementPredictionSyncPacket.class, ClientMovementPredictionSyncSerializer_v786.INSTANCE)
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v786(SOUND_EVENTS))
            .updateSerializer(SetHudPacket.class, SetHudSerializer_v786.INSTANCE)
            .registerPacket(UpdateClientOptionsPacket::new, UpdateClientOptionsSerializer_v786.INSTANCE, 323, PacketRecipient.SERVER)
            .registerPacket(PlayerVideoCapturePacket::new, PlayerVideoCaptureSerializer_v786.INSTANCE, 324, PacketRecipient.CLIENT)
            .registerPacket(PlayerUpdateEntityOverridesPacket::new, PlayerUpdateEntityOverridesSerializer_v786.INSTANCE, 325, PacketRecipient.CLIENT)
            .deregisterPacket(LevelSoundEvent1Packet.class) // removed in favor of LevelSoundEventPacket
            .deregisterPacket(LevelSoundEvent2Packet.class) // removed in favor of LevelSoundEventPacket
            .build();
}
