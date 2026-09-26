package org.cloudburstmc.protocol.bedrock.codec.v503.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.education.AgentActionType;
import org.cloudburstmc.protocol.bedrock.packet.AgentActionEventPacket;

public class AgentActionEventSerializer_v503 implements BedrockPacketSerializer<AgentActionEventPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AgentActionEventPacket packet) {
        helper.writeString(buffer, packet.getRequestId());
        buffer.writeIntLE(packet.getAction().ordinal());
        helper.writeString(buffer, packet.getResponse());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AgentActionEventPacket packet) {
        packet.setRequestId(helper.readString(buffer));
        packet.setAction(AgentActionType.from(buffer.readIntLE()));
        packet.setResponse(helper.readString(buffer));
    }
}