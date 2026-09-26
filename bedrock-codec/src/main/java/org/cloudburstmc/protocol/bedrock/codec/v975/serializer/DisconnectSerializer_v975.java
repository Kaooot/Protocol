package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v712.serializer.DisconnectSerializer_v712;
import org.cloudburstmc.protocol.bedrock.data.connection.DisconnectFailReason;
import org.cloudburstmc.protocol.bedrock.data.connection.DisconnectPacketMessages;
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class DisconnectSerializer_v975 extends DisconnectSerializer_v712 {

    public static final DisconnectSerializer_v975 INSTANCE = new DisconnectSerializer_v975();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        VarInts.writeInt(buffer, packet.getReason().ordinal());
        DisconnectPacketMessages messages = packet.getMessages();
        VarInts.writeUnsignedInt(buffer, messages == null ? 1 : 0); //oneOf<DisconnectPacketMessages, null>
        if (messages != null) {
            helper.writeString(buffer, messages.getMessage());
            helper.writeString(buffer, messages.getFilteredMessage());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        packet.setReason(DisconnectFailReason.values()[VarInts.readInt(buffer)]);
        int type = VarInts.readUnsignedInt(buffer);
        if (type == 0) {
            DisconnectPacketMessages messages = new DisconnectPacketMessages();
            messages.setMessage(helper.readString(buffer));
            messages.setFilteredMessage(helper.readString(buffer));
            packet.setMessages(messages);
        }
    }
}
