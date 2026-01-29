package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.AimAssistActorPriorityData;
import org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistActorPriorityPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraAimAssistActorPrioritySerializer_v924 implements BedrockPacketSerializer<CameraAimAssistActorPriorityPacket> {
    public static final CameraAimAssistActorPrioritySerializer_v924 INSTANCE = new CameraAimAssistActorPrioritySerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistActorPriorityPacket packet) {
        helper.writeArray(buffer, packet.getCameraAimAssistActorPriorityList(), this::writeAimAssistActorPriorityData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistActorPriorityPacket packet) {
        helper.readArray(buffer, packet.getCameraAimAssistActorPriorityList(), this::readAimAssistActorPriorityData);
    }

    protected void writeAimAssistActorPriorityData(ByteBuf buffer, BedrockCodecHelper helper, AimAssistActorPriorityData data) {
        buffer.writeIntLE(data.getPresetIndex());
        buffer.writeIntLE(data.getCategoryIndex());
        buffer.writeIntLE(data.getActorIndex());
        buffer.writeIntLE(data.getPriorityValue());
    }

    protected AimAssistActorPriorityData readAimAssistActorPriorityData(ByteBuf buffer, BedrockCodecHelper helper) {
        final AimAssistActorPriorityData data = new AimAssistActorPriorityData();
        data.setPresetIndex(buffer.readIntLE());
        data.setCategoryIndex(buffer.readIntLE());
        data.setActorIndex(buffer.readIntLE());
        data.setPriorityValue(buffer.readIntLE());
        return data;
    }
}