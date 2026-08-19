package org.cloudburstmc.protocol.bedrock.netty.codec.packet;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.netty.BedrockPacketWrapper;

public class BedrockPacketCodec_v1 extends BedrockPacketCodec {

    public BedrockPacketCodec_v1() {
        super(true);
    }

    public BedrockPacketCodec_v1(boolean shouldLogEncodingErrors) {
        super(shouldLogEncodingErrors);
    }

    @Override
    public void encodeHeader(ByteBuf buf, BedrockPacketWrapper msg) {
        buf.writeByte(msg.getPacketId());
    }

    @Override
    public void decodeHeader(ByteBuf buf, BedrockPacketWrapper msg) {
        msg.setPacketId(buf.readUnsignedByte());
    }
}
