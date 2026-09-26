package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.CameraAimAssistPresetsSerializer_v776;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetsPacketOperation;
import org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistPresetsPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraAimAssistPresetsSerializer_v800 extends CameraAimAssistPresetsSerializer_v776 {

    public static final CameraAimAssistPresetsSerializer_v800 INSTANCE = new CameraAimAssistPresetsSerializer_v800();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetsPacket packet) {
        helper.writeArray(buffer, packet.getCameraAimAssistCategories(), this::writeCategory);
        helper.writeArray(buffer, packet.getCameraAimAssistPresets(), this::writePreset);
        buffer.writeByte(packet.getOperation().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetsPacket packet) {
        helper.readArray(buffer, packet.getCameraAimAssistCategories(), this::readCategory);
        helper.readArray(buffer, packet.getCameraAimAssistPresets(), this::readPreset);
        packet.setOperation(CameraAimAssistPresetsPacketOperation.from(buffer.readUnsignedByte()));
    }
}