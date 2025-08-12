package org.cloudburstmc.protocol.bedrock.codec.v630.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerPlayerPostMovePositionPacket;

/**
 * @author Kaooot
 */
public class ServerPlayerPostMovePositionSerializer_v630 implements BedrockPacketSerializer<ServerPlayerPostMovePositionPacket> {
    public static final ServerPlayerPostMovePositionSerializer_v630 INSTANCE = new ServerPlayerPostMovePositionSerializer_v630();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerPlayerPostMovePositionPacket packet) {
        helper.writeVector3f(buffer, packet.getPos());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerPlayerPostMovePositionPacket packet) {
        packet.setPos(helper.readVector3f(buffer));
    }
}