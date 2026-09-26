package org.cloudburstmc.protocol.bedrock.codec.v486.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ScriptMessagePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScriptMessageSerializer_v486 implements BedrockPacketSerializer<ScriptMessagePacket> {

    public static final ScriptMessageSerializer_v486 INSTANCE = new ScriptMessageSerializer_v486();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ScriptMessagePacket packet) {
        helper.writeString(buffer, packet.getMessageId());
        helper.writeString(buffer, packet.getMessageValue());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ScriptMessagePacket packet) {
        packet.setMessageId(helper.readString(buffer));
        packet.setMessageValue(helper.readString(buffer));
    }
}
