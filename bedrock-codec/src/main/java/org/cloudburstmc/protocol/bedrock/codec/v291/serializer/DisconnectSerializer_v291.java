package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.connection.DisconnectPacketMessages;
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket;

public class DisconnectSerializer_v291 implements BedrockPacketSerializer<DisconnectPacket> {
    public static final DisconnectSerializer_v291 INSTANCE = new DisconnectSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        DisconnectPacketMessages messages = packet.getMessages();
        buffer.writeBoolean(messages == null);
        if (messages != null) {
            helper.writeString(buffer, messages.getMessage());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        boolean skipMessage = buffer.readBoolean();
        if (!skipMessage) {
            DisconnectPacketMessages messages = new DisconnectPacketMessages();
            messages.setMessage(helper.readString(buffer));
            packet.setMessages(messages);
        }
    }
}
