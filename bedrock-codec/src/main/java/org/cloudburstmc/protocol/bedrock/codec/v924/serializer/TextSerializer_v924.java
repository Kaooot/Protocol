package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.TextSerializer_v898;
import org.cloudburstmc.protocol.bedrock.data.text.TextPacketBodyType;
import org.cloudburstmc.protocol.bedrock.data.text.TextPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextSerializer_v924 extends TextSerializer_v898 {
    public static final TextSerializer_v924 INSTANCE = new TextSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        buffer.writeBoolean(packet.isLocalize());
        buffer.writeByte(TextPacketBodyType.from(packet.getBody().getClass()).ordinal());
        buffer.writeByte(packet.getMessageType().ordinal());
        this.writeBody(buffer, helper, packet);
        helper.writeString(buffer, packet.getSendersXUID());
        helper.writeString(buffer, packet.getPlatformId());
        final String filteredMessage = helper.getTextConverter().serialize(
                packet.getFilteredMessage(CharSequence.class)
        );
        helper.writeOptional(buffer, s -> !s.isEmpty(), filteredMessage, helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        packet.setLocalize(buffer.readBoolean());
        final TextPacketBodyType bodyType = TextPacketBodyType.from(buffer.readUnsignedByte());
        packet.setMessageType(TextPacketType.from(buffer.readUnsignedByte()));
        this.readBody(buffer, helper, packet, bodyType);
        packet.setSendersXUID(helper.readStringMaxLen(buffer, 64));
        packet.setPlatformId(helper.readStringMaxLen(buffer, 256));
        packet.setFilteredMessage(
                helper.readOptional(
                        buffer,
                        "",
                        (buf, codecHelper) ->
                                codecHelper.getTextConverter().deserialize(codecHelper.readString(buf), packet.isLocalize())
                )
        );
    }
}
