package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.TextSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.text.MessageAndParams;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextSerializer_v2259 extends TextSerializer_v924 {
    public static final TextSerializer_v2259 INSTANCE = new TextSerializer_v2259();

    @Override
    protected void writeMessageAndParams(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet, MessageAndParams messageAndParams) {
        super.writeMessageAndParams(buffer, helper, packet, messageAndParams);
        buffer.writeBoolean(messageAndParams.isQueue());
    }

    @Override
    protected MessageAndParams readMessageAndParams(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        final MessageAndParams messageAndParams = super.readMessageAndParams(buffer, helper, packet);
        messageAndParams.setQueue(buffer.readBoolean());
        return messageAndParams;
    }
}