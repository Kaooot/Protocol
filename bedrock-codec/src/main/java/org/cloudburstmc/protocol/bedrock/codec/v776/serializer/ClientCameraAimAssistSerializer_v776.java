package org.cloudburstmc.protocol.bedrock.codec.v776.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.AimAssistAction;
import org.cloudburstmc.protocol.bedrock.packet.ClientCameraAimAssistPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientCameraAimAssistSerializer_v776 implements BedrockPacketSerializer<ClientCameraAimAssistPacket> {
    public static final ClientCameraAimAssistSerializer_v776 INSTANCE = new ClientCameraAimAssistSerializer_v776();

    protected static final AimAssistAction[] ACTIONS = AimAssistAction.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCameraAimAssistPacket packet) {
        helper.writeString(buffer, packet.getCameraPresetId());
        buffer.writeByte(packet.getAction().ordinal());
        buffer.writeBoolean(packet.isAllowAimAssist());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCameraAimAssistPacket packet) {
        packet.setCameraPresetId(helper.readString(buffer));
        packet.setAction(ACTIONS[buffer.readUnsignedByte()]);
        packet.setAllowAimAssist(buffer.readBoolean());
    }
}