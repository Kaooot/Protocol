package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.TextSerializer_v898;
import org.cloudburstmc.protocol.bedrock.data.text.*;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

public class TextSerializer_v924 extends TextSerializer_v898 {
    public static final TextSerializer_v924 INSTANCE = new TextSerializer_v924();

    protected TextSerializer_v924() {
        this.bodyVariant = this.bodyVariant.toBuilder(
                        TextPacketBodyType::ordinal,
                        (buffer, helper, owner, value) -> buffer.writeByte(value),
                        (buffer, helper, owner) -> (int) buffer.readUnsignedByte()
                )
                .prefix(
                        (buffer, helper, owner, value) -> buffer.writeByte(owner.getMessageType().ordinal()),
                        (buffer, helper, owner) -> {
                            owner.setMessageType(TextPacketType.from(buffer.readUnsignedByte()));
                            return -1;
                        }
                )
                .build();
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        buffer.writeBoolean(packet.isLocalize());
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