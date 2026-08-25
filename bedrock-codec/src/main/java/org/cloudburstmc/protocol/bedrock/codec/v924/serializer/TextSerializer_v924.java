package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.TextSerializer_v898;
import org.cloudburstmc.protocol.bedrock.data.TextPacketBodyType;
import org.cloudburstmc.protocol.bedrock.data.TextPacketType;
import org.cloudburstmc.protocol.bedrock.data.payload.text.TextPacketBody;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextSerializer_v924 extends TextSerializer_v898 {
    public static final TextSerializer_v924 INSTANCE = new TextSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        buffer.writeBoolean(packet.isLocalize());

        final TextPacketBody body = packet.getBody();
        final TextPacketBodyType bodyType = body.getType();

        buffer.writeByte(bodyType.ordinal());
        buffer.writeByte(packet.getMessageType().ordinal());
        this.writeMessageBody(buffer, helper, body);
        helper.writeString(buffer, packet.getSendersXUID());
        helper.writeString(buffer, packet.getPlatformId());
        helper.writeOptional(
                buffer,
                o -> !packet.getFilteredMessage().isEmpty(),
                packet.getFilteredMessage(),
                helper::writeString
        );
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        packet.setLocalize(buffer.readBoolean());

        final TextPacketBodyType bodyType = TextPacketBodyType.values()[buffer.readUnsignedByte()];
        final TextPacketType messageType = TextPacketType.from(buffer.readUnsignedByte());
        packet.setMessageType(messageType);
        packet.setBody(this.readMessageBody(buffer, helper, bodyType));
        packet.setSendersXUID(helper.readStringMaxLen(buffer, 64));
        packet.setPlatformId(helper.readStringMaxLen(buffer, 256));
        packet.setFilteredMessage(helper.readOptional(buffer, "", helper::readString));
    }
}