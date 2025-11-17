package org.cloudburstmc.protocol.bedrock.codec.v554.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.TextSerializer_v332;
import org.cloudburstmc.protocol.bedrock.data.TextPacketType;
import org.cloudburstmc.protocol.bedrock.data.payload.text.AuthorAndMessage;
import org.cloudburstmc.protocol.bedrock.data.payload.text.MessageAndParams;
import org.cloudburstmc.protocol.bedrock.data.payload.text.MessageOnly;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

public class TextSerializer_v554 extends TextSerializer_v332 {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        final TextPacketType messageType = packet.getMessageType();
        buffer.writeByte(messageType.ordinal());
        buffer.writeBoolean(packet.isLocalize());

        switch (messageType) {
            case CHAT:
            case WHISPER:
            case ANNOUNCEMENT:
                helper.writeString(buffer, ((AuthorAndMessage) packet.getBody()).getPlayerName());
            case RAW:
            case TIP:
            case SYSTEM_MESSAGE:
            case TEXT_OBJECT:
            case TEXT_OBJECT_WHISPER:
            case TEXT_OBJECT_ANNOUNCEMENT:
                helper.writeString(buffer, packet.getBody().getMessage());
                break;
            case TRANSLATE:
            case POPUP:
            case JUKEBOX_POPUP:
                helper.writeString(buffer, packet.getBody().getMessage());
                helper.writeArray(buffer, ((MessageAndParams) packet.getBody()).getParameterList(), helper::writeString);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported TextPacketType " + messageType);
        }

        helper.writeString(buffer, packet.getSendersXUID());
        helper.writeString(buffer, packet.getPlatformId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        final TextPacketType messageType = TextPacketType.from(buffer.readUnsignedByte());
        packet.setMessageType(messageType);
        packet.setLocalize(buffer.readBoolean());

        switch (messageType) {
            case CHAT:
            case WHISPER:
            case ANNOUNCEMENT:
                final AuthorAndMessage authorAndMessage = new AuthorAndMessage();
                authorAndMessage.setPlayerName(helper.readString(buffer));
                authorAndMessage.setMessage(helper.readString(buffer));
                packet.setBody(authorAndMessage);
                break;
            case RAW:
            case TIP:
            case SYSTEM_MESSAGE:
            case TEXT_OBJECT:
            case TEXT_OBJECT_WHISPER:
            case TEXT_OBJECT_ANNOUNCEMENT:
                final MessageOnly messageOnly = new MessageOnly();
                messageOnly.setMessage(helper.readString(buffer));
                packet.setBody(messageOnly);
                break;
            case TRANSLATE:
            case POPUP:
            case JUKEBOX_POPUP:
                final MessageAndParams messageAndParams = new MessageAndParams();
                messageAndParams.setMessage(helper.readString(buffer));
                helper.readArray(buffer, messageAndParams.getParameterList(), helper::readString);
                packet.setBody(messageAndParams);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported TextPacketType " + messageType);
        }

        packet.setSendersXUID(helper.readString(buffer));
        packet.setPlatformId(helper.readString(buffer));
    }
}