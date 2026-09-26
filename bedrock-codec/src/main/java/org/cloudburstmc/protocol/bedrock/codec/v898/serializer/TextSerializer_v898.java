package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v685.serializer.TextSerializer_v685;
import org.cloudburstmc.protocol.bedrock.data.text.TextPacketBodyType;
import org.cloudburstmc.protocol.bedrock.data.text.TextPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextSerializer_v898 extends TextSerializer_v685 {
    public static final TextSerializer_v898 INSTANCE = new TextSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        buffer.writeBoolean(packet.isLocalize());

        final TextPacketBodyType bodyType = TextPacketBodyType.from(packet.getBody().getClass());
        buffer.writeByte(bodyType.ordinal());
        for (final TextPacketType type : bodyType.getTypes()) {
            helper.writeString(buffer, type.getId());
        }

        buffer.writeByte(packet.getMessageType().ordinal());
        this.bodyVariant.write(buffer, helper, packet, packet.getBody());
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
        for (int i = 0; i < bodyType.getTypes().size(); i++) {
            final TextPacketType type = TextPacketType.from(helper.readString(buffer));
            if (!bodyType.getTypes().contains(type)) {
                throw new IllegalStateException("TextPacketType is not in the types list of TextPacketBodyType " + bodyType);
            }
        }

        final TextPacketType messageType = TextPacketType.from(buffer.readUnsignedByte());
        packet.setMessageType(messageType);
        packet.setBody(this.bodyVariant.read(buffer, helper, packet));
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