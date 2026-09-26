package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.text.*;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextSerializer_v291 implements BedrockPacketSerializer<TextPacket> {
    public static final TextSerializer_v291 INSTANCE = new TextSerializer_v291();

    protected VariantCodec<TextPacket> bodyVariant = VariantCodec.<TextPacketBodyType, TextPacket>builder(
                    TextPacketBodyType::ordinal,
                    (buffer, helper, owner, value) -> {
                    },
                    (buffer, helper, owner) -> TextPacketBodyType.from(owner.getMessageType()).ordinal()
            )
            .add(
                    TextPacketBodyType.MESSAGE_ONLY,
                    MessageOnly.class,
                    this::writeMessageOnly,
                    this::readMessageOnly
            )
            .add(
                    TextPacketBodyType.AUTHOR_AND_MESSAGE,
                    AuthorAndMessage.class,
                    this::writeAuthorAndMessage,
                    this::readAuthorAndMessage
            )
            .add(
                    TextPacketBodyType.MESSAGE_AND_PARAMS,
                    MessageAndParams.class,
                    this::writeMessageAndParams,
                    this::readMessageAndParams
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        buffer.writeByte(packet.getMessageType().ordinal());
        buffer.writeBoolean(packet.isLocalize());
        this.bodyVariant.write(buffer, helper, packet, packet.getBody());
        helper.writeString(buffer, packet.getSendersXUID());
        helper.writeString(buffer, packet.getPlatformId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        packet.setMessageType(TextPacketType.from(buffer.readUnsignedByte()));
        packet.setLocalize(buffer.readBoolean());
        packet.setBody(this.bodyVariant.read(buffer, helper, packet));
        packet.setSendersXUID(helper.readStringMaxLen(buffer, 64));
        packet.setPlatformId(helper.readStringMaxLen(buffer, 256));
    }

    protected void writeMessageOnly(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet, MessageOnly messageOnly) {
        final boolean isJson = packet.getMessageType().equals(TextPacketType.TEXT_OBJECT) ||
                packet.getMessageType().equals(TextPacketType.TEXT_OBJECT_ANNOUNCEMENT) ||
                packet.getMessageType().equals(TextPacketType.TEXT_OBJECT_WHISPER);
        helper.writeString(
                buffer,
                isJson ? helper.getTextConverter().serializeJson(messageOnly.getMessage(CharSequence.class)) :
                        helper.getTextConverter().serialize(messageOnly.getMessage(CharSequence.class))
        );
    }

    protected MessageOnly readMessageOnly(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        final boolean isJson = packet.getMessageType().equals(TextPacketType.TEXT_OBJECT) ||
                packet.getMessageType().equals(TextPacketType.TEXT_OBJECT_ANNOUNCEMENT) ||
                packet.getMessageType().equals(TextPacketType.TEXT_OBJECT_WHISPER);
        final boolean localize = packet.isLocalize();
        final String message = helper.readString(buffer);
        final MessageOnly messageOnly = new MessageOnly();
        messageOnly.setMessage(
                isJson ? helper.getTextConverter().deserializeJson(message, localize) :
                        helper.getTextConverter().deserialize(message, localize)
        );
        return messageOnly;
    }

    protected void writeAuthorAndMessage(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet, AuthorAndMessage authorAndMessage) {
        helper.writeString(buffer, authorAndMessage.getPlayerName());
        helper.writeString(buffer, helper.getTextConverter().serialize(authorAndMessage.getMessage(CharSequence.class)));
    }

    protected AuthorAndMessage readAuthorAndMessage(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        final AuthorAndMessage authorAndMessage = new AuthorAndMessage();
        authorAndMessage.setPlayerName(helper.readStringMaxLen(buffer, 256));
        authorAndMessage.setMessage(
                helper.getTextConverter().deserialize(
                        helper.readStringMaxLen(buffer, 65536),
                        packet.isLocalize()
                )
        );
        return authorAndMessage;
    }

    protected void writeMessageAndParams(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet, MessageAndParams messageAndParams) {
        helper.writeString(
                buffer,
                helper.getTextConverter().serializeWithArguments(
                        messageAndParams.getMessage(CharSequence.class),
                        messageAndParams.getParameterList()
                )
        );
        helper.writeArray(buffer, messageAndParams.getParameterList(), helper::writeString);
    }

    protected MessageAndParams readMessageAndParams(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        final MessageAndParams messageAndParams = new MessageAndParams();
        final String message = helper.readString(buffer);
        helper.readArray(buffer, messageAndParams.getParameterList(), helper::readString);
        messageAndParams.setMessage(
                helper.getTextConverter().deserializeWithArguments(
                        message,
                        messageAndParams.getParameterList(),
                        packet.isLocalize()
                )
        );
        return messageAndParams;
    }
}