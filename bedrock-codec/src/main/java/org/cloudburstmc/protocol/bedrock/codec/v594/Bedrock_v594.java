package org.cloudburstmc.protocol.bedrock.codec.v594;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v575.BedrockCodecHelper_v575;
import org.cloudburstmc.protocol.bedrock.codec.v575.Bedrock_v575;
import org.cloudburstmc.protocol.bedrock.codec.v582.Bedrock_v582;
import org.cloudburstmc.protocol.bedrock.codec.v589.Bedrock_v589;
import org.cloudburstmc.protocol.bedrock.codec.v594.serializer.AgentAnimationSerializer_v594;
import org.cloudburstmc.protocol.bedrock.codec.v594.serializer.AvailableCommandsSerializer_v594;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataTypes;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlag;
import org.cloudburstmc.protocol.bedrock.packet.AgentAnimationPacket;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;
import org.cloudburstmc.protocol.bedrock.packet.ScriptCustomEventPacket;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v594 extends Bedrock_v589 {

    protected static final TypeMap<ActorFlag> ENTITY_FLAGS = Bedrock_v575.ENTITY_FLAGS
            .toBuilder()
            .insert(114, ActorFlag.CRAWLING)
            .build();

    protected static final ActorDataTypeMap ENTITY_DATA = Bedrock_v589.ENTITY_DATA
            .toBuilder()
            .insert(ActorDataTypes.COLLISION_BOX, 130, ActorDataFormat.VECTOR3F)
            .update(ActorDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0))
            .update(ActorDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1))
            .build();

    protected static final TypeMap<CommandParam> COMMAND_PARAMS = Bedrock_v582.COMMAND_PARAMS.toBuilder()
            .insert(134217728, CommandParam.CHAINED_COMMAND)
            .build();

    @SuppressWarnings("deprecation")
    public static final BedrockCodec CODEC = Bedrock_v589.CODEC.toBuilder()
            .raknetProtocolVersion(11)
            .protocolVersion(594)
            .minecraftVersion("1.20.10")
            .helper(() -> new BedrockCodecHelper_v575(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .deregisterPacket(ScriptCustomEventPacket.class)
            .updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v594(COMMAND_PARAMS))
            .registerPacket(AgentAnimationPacket::new, new AgentAnimationSerializer_v594(), 304, PacketRecipient.CLIENT)
            .build();
}
