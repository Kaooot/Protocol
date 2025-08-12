package org.cloudburstmc.protocol.bedrock.codec.v594.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.AgentAnimationPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class AgentAnimationSerializer_v594 implements BedrockPacketSerializer<AgentAnimationPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AgentAnimationPacket packet) {
        buffer.writeByte(packet.getAgentAnimation());
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AgentAnimationPacket packet) {
        packet.setAgentAnimation(buffer.readByte());
        packet.setRuntimeId(VarInts.readUnsignedLong(buffer));
    }
}
