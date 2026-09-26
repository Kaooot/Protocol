package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.AimAssistActorPriorityData;
import org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistActorPriorityPacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraAimAssistActorPrioritySerializer_v924 implements BedrockPacketSerializer<CameraAimAssistActorPriorityPacket> {

    public static final CameraAimAssistActorPrioritySerializer_v924 INSTANCE = new CameraAimAssistActorPrioritySerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistActorPriorityPacket packet) {
        helper.writeArray(buffer, packet.getCameraAimassistActorPriorityList(), (buf, value) -> {
            buf.writeIntLE(value.getPresetIndex());
            buf.writeIntLE(value.getCategoryIndex());
            buf.writeIntLE(value.getActorIndex());
            buf.writeIntLE(value.getPriorityValue());
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistActorPriorityPacket packet) {
        helper.readArray(buffer, packet.getCameraAimassistActorPriorityList(), (buf, h) -> {
            final AimAssistActorPriorityData data = new AimAssistActorPriorityData();
            data.setPresetIndex(buf.readIntLE());
            data.setCategoryIndex(buf.readIntLE());
            data.setActorIndex(buf.readIntLE());
            data.setPriorityValue(buf.readIntLE());
            return data;
        });
    }
}