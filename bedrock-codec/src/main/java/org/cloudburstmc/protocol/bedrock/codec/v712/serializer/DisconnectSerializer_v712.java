package org.cloudburstmc.protocol.bedrock.codec.v712.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.connection.DisconnectFailReason;
import org.cloudburstmc.protocol.bedrock.data.connection.DisconnectPacketMessages;
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class DisconnectSerializer_v712 implements BedrockPacketSerializer<DisconnectPacket> {
    public static final DisconnectSerializer_v712 INSTANCE = new DisconnectSerializer_v712();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        VarInts.writeInt(buffer, packet.getReason().ordinal());
        DisconnectPacketMessages messages = packet.getMessages();
        buffer.writeBoolean(messages == null);
        if (messages != null) {
            helper.writeString(buffer, messages.getMessage());
            helper.writeString(buffer, messages.getFilteredMessage());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        packet.setReason(DisconnectFailReason.values()[VarInts.readInt(buffer)]);
        boolean skipMessage = buffer.readBoolean();
        if (!skipMessage) {
            DisconnectPacketMessages messages = new DisconnectPacketMessages();
            messages.setMessage(helper.readString(buffer));
            messages.setFilteredMessage(helper.readString(buffer));
            packet.setMessages(messages);
        }
    }
}
