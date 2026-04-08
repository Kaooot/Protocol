package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.connection.DisconnectPacketMessages;
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisconnectSerializer_v291 implements BedrockPacketSerializer<DisconnectPacket> {
    public static final DisconnectSerializer_v291 INSTANCE = new DisconnectSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        buffer.writeBoolean(packet.isSkipMessage());
        if (!packet.isSkipMessage()) {
            this.writeMessages(buffer, helper, packet.getMessages());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
        packet.setSkipMessage(buffer.readBoolean());
        if (!packet.isSkipMessage()) {
            this.readMessages(buffer, helper);
        }
    }

    protected void writeMessages(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacketMessages messages) {
        helper.writeString(buffer, messages.getMessage());
    }

    protected DisconnectPacketMessages readMessages(ByteBuf buffer, BedrockCodecHelper helper) {
        final String message = helper.readString(buffer);
        return new DisconnectPacketMessages(message, null);
    }
}
