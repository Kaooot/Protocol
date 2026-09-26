package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.connection.WebSocketPacketData;
import org.cloudburstmc.protocol.bedrock.packet.AutomationClientConnectPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AutomationClientConnectSerializer_v291 implements BedrockPacketSerializer<AutomationClientConnectPacket> {
    public static final AutomationClientConnectSerializer_v291 INSTANCE = new AutomationClientConnectSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AutomationClientConnectPacket packet) {
        helper.writeString(buffer, packet.getWebSocketData().getWebsocketServerURI());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AutomationClientConnectPacket packet) {
        final WebSocketPacketData data = new WebSocketPacketData();
        data.setWebsocketServerURI(helper.readString(buffer));

        packet.setWebSocketData(data);
    }
}