package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.chat.ChatFormatting;
import org.cloudburstmc.protocol.bedrock.data.chat.SignedChat;
import org.cloudburstmc.protocol.bedrock.packet.ChatPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatSerializer_v2259 implements BedrockPacketSerializer<ChatPacket> {
    public static final ChatSerializer_v2259 INSTANCE = new ChatSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ChatPacket packet) {
        this.writeSignedChat(buffer, helper, packet.getSignedChat());
        helper.writeOptionalNull(buffer, packet.getRoomId(), helper::writeString);
        helper.writeOptionalNull(buffer, packet.getFormatting(), this::writeChatFormatting);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ChatPacket packet) {
        packet.setSignedChat(this.readSignedChat(buffer, helper));
        packet.setRoomId(helper.readOptional(buffer, null, helper::readString));
        packet.setFormatting(helper.readOptional(buffer, null, this::readChatFormatting));
    }

    protected void writeSignedChat(ByteBuf buffer, BedrockCodecHelper helper, SignedChat signedChat) {
        helper.writeString(buffer, signedChat.getKeyId());
        helper.writeString(buffer, signedChat.getSenderId());
        helper.writeString(buffer, signedChat.getMessageId());
        helper.writeString(buffer, signedChat.getScanText());
        helper.writeString(buffer, signedChat.getRedactedText());
        helper.writeString(buffer, signedChat.getAuthorName());
        helper.writeString(buffer, signedChat.getSignature());
    }

    protected SignedChat readSignedChat(ByteBuf buffer, BedrockCodecHelper helper) {
        final SignedChat signedChat = new SignedChat();
        signedChat.setKeyId(helper.readString(buffer));
        signedChat.setSenderId(helper.readString(buffer));
        signedChat.setMessageId(helper.readString(buffer));
        signedChat.setScanText(helper.readString(buffer));
        signedChat.setRedactedText(helper.readString(buffer));
        signedChat.setAuthorName(helper.readString(buffer));
        signedChat.setSignature(helper.readString(buffer));
        return signedChat;
    }

    protected void writeChatFormatting(ByteBuf buffer, BedrockCodecHelper helper, ChatFormatting chatFormatting) {
        helper.writeOptionalNull(buffer, chatFormatting.getNamePrefix(), helper::writeString);
        helper.writeOptionalNull(buffer, chatFormatting.getNamePostfix(), helper::writeString);
        helper.writeOptionalNull(buffer, chatFormatting.getMessagePrefix(), helper::writeString);
        helper.writeOptionalNull(buffer, chatFormatting.getMessagePostfix(), helper::writeString);
    }

    protected ChatFormatting readChatFormatting(ByteBuf buffer, BedrockCodecHelper helper) {
        final ChatFormatting chatFormatting = new ChatFormatting();
        chatFormatting.setNamePrefix(helper.readOptional(buffer, null, helper::readString));
        chatFormatting.setNamePostfix(helper.readOptional(buffer, null, helper::readString));
        chatFormatting.setMessagePrefix(helper.readOptional(buffer, null, helper::readString));
        chatFormatting.setMessagePostfix(helper.readOptional(buffer, null, helper::readString));
        return chatFormatting;
    }
}