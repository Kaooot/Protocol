package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.TextPacketBodyType;
import org.cloudburstmc.protocol.bedrock.data.TextPacketType;
import org.cloudburstmc.protocol.bedrock.data.payload.text.AuthorAndMessage;
import org.cloudburstmc.protocol.bedrock.data.payload.text.MessageAndParams;
import org.cloudburstmc.protocol.bedrock.data.payload.text.MessageOnly;
import org.cloudburstmc.protocol.bedrock.data.payload.text.TextPacketBody;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextSerializer_v898 implements BedrockPacketSerializer<TextPacket> {
    public static final TextSerializer_v898 INSTANCE = new TextSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        buffer.writeBoolean(packet.isLocalize());

        final TextPacketBody body = packet.getBody();
        final TextPacketBodyType bodyType = body.getType();

        buffer.writeByte(bodyType.ordinal());

        for (TextPacketType type : bodyType.getTypes()) {
            helper.writeString(buffer, type.getId());
        }

        buffer.writeByte(packet.getMessageType().ordinal());
        this.writeMessageBody(buffer, helper, body);
        helper.writeString(buffer, packet.getSendersXUID());
        helper.writeString(buffer, packet.getPlatformId());
        helper.writeString(buffer, packet.getFilteredMessage());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        packet.setLocalize(buffer.readBoolean());

        final TextPacketBodyType bodyType = TextPacketBodyType.values()[buffer.readUnsignedByte()];

        for (int i = 0; i < bodyType.getTypes().size(); i++) {
            final TextPacketType type = TextPacketType.from(helper.readString(buffer));
            if (!bodyType.getTypes().contains(type)) {
                throw new IllegalStateException("TextPacketType is not in the types list of TextPacketBodyType " + bodyType);
            }
        }

        final TextPacketType messageType = TextPacketType.from(buffer.readUnsignedByte());
        packet.setMessageType(messageType);
        packet.setBody(this.readMessageBody(buffer, helper, bodyType));
        packet.setSendersXUID(helper.readString(buffer));
        packet.setPlatformId(helper.readString(buffer));
        packet.setFilteredMessage(helper.readString(buffer));
    }

    protected void writeMessageBody(ByteBuf buffer, BedrockCodecHelper helper, TextPacketBody body) {
        switch (body.getType()) {
            case MESSAGE_ONLY:
                this.writeMessageOnly(buffer, helper, (MessageOnly) body);
                break;
            case AUTHOR_AND_MESSAGE:
                this.writeAuthorAndMessage(buffer, helper, (AuthorAndMessage) body);
                break;
            case MESSAGE_AND_PARAMS:
                this.writeMessageAndParams(buffer, helper, (MessageAndParams) body);
                break;
        }
    }

    protected TextPacketBody readMessageBody(ByteBuf buffer, BedrockCodecHelper helper, TextPacketBodyType type) {
        switch (type) {
            case MESSAGE_ONLY:
                return this.readMessageOnly(buffer, helper);
            case AUTHOR_AND_MESSAGE:
                return this.readAuthorAndMessage(buffer, helper);
            case MESSAGE_AND_PARAMS:
                return this.readMessageAndParams(buffer, helper);
            default:
                throw new IllegalStateException("Unknown TextPacketBodyType.");
        }
    }

    protected void writeMessageOnly(ByteBuf buffer, BedrockCodecHelper helper, MessageOnly messageOnly) {
        helper.writeString(buffer, messageOnly.getMessage());
    }

    protected MessageOnly readMessageOnly(ByteBuf buffer, BedrockCodecHelper helper) {
        final MessageOnly messageOnly = new MessageOnly();
        messageOnly.setMessage(helper.readString(buffer));
        return messageOnly;
    }

    protected void writeAuthorAndMessage(ByteBuf buffer, BedrockCodecHelper helper, AuthorAndMessage authorAndMessage) {
        helper.writeString(buffer, authorAndMessage.getPlayerName());
        helper.writeString(buffer, authorAndMessage.getMessage());
    }

    protected AuthorAndMessage readAuthorAndMessage(ByteBuf buffer, BedrockCodecHelper helper) {
        final AuthorAndMessage authorAndMessage = new AuthorAndMessage();
        authorAndMessage.setPlayerName(helper.readString(buffer));
        authorAndMessage.setMessage(helper.readString(buffer));
        return authorAndMessage;
    }

    protected void writeMessageAndParams(ByteBuf buffer, BedrockCodecHelper helper, MessageAndParams messageAndParams) {
        helper.writeString(buffer, messageAndParams.getMessage());
        helper.writeArray(buffer, messageAndParams.getParameterList(), helper::writeString);
    }

    protected MessageAndParams readMessageAndParams(ByteBuf buffer, BedrockCodecHelper helper) {
        final MessageAndParams messageAndParams = new MessageAndParams();
        messageAndParams.setMessage(helper.readString(buffer));
        helper.readArray(buffer, messageAndParams.getParameterList(), helper::readString);
        return messageAndParams;
    }
}