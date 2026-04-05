package org.cloudburstmc.protocol.bedrock.codec.v712.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.DisconnectFailReason;
import org.cloudburstmc.protocol.bedrock.data.payload.connection.DisconnectPacketMessages;
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class DisconnectSerializer_v712 implements BedrockPacketSerializer<DisconnectPacket> {
    public static final DisconnectSerializer_v712 INSTANCE = new DisconnectSerializer_v712();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        VarInts.writeInt(buffer, packet.getReason().ordinal());
        buffer.writeBoolean(packet.isSkipMessage());
        if (!packet.isSkipMessage()) {
            this.writeMessages(buffer, helper, packet.getMessages());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        packet.setReason(DisconnectFailReason.values()[VarInts.readInt(buffer)]);
        packet.setSkipMessage(buffer.readBoolean());
        if (!packet.isSkipMessage()) {
            packet.setMessages(this.readMessages(buffer, helper));
        }
    }

    protected void writeMessages(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacketMessages messages) {
        helper.writeString(buffer, messages.getMessage());
        helper.writeString(buffer, messages.getFilteredMessage());
    }

    protected DisconnectPacketMessages readMessages(ByteBuf buffer, BedrockCodecHelper helper) {
        final String message = helper.readString(buffer);
        final String filteredMessage = helper.readString(buffer);
        return new DisconnectPacketMessages(message, filteredMessage);
    }
}