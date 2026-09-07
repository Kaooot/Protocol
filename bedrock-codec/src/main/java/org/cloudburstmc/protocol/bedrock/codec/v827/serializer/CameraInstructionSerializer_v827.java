package org.cloudburstmc.protocol.bedrock.codec.v827.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.CameraInstructionSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.camera.instruction.CameraFovInstruction;
import org.cloudburstmc.protocol.bedrock.packet.CameraInstructionPacket;

public class CameraInstructionSerializer_v827 extends CameraInstructionSerializer_v818 {

    public static final CameraInstructionSerializer_v827 INSTANCE = new CameraInstructionSerializer_v827();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getCameraInstruction().getFieldOfView(), this::writeCameraFovInstruction);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.getCameraInstruction().setFieldOfView(helper.readOptional(buffer, null, this::readCameraFovInstruction));
    }

    protected void writeCameraFovInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraFovInstruction fovInstruction) {
        buffer.writeFloatLE(fovInstruction.getFieldOfView());
        buffer.writeFloatLE(fovInstruction.getFovEaseTime());
        buffer.writeByte(fovInstruction.getFovEaseType().ordinal());
        buffer.writeBoolean(fovInstruction.isFieldOfViewClear());
    }

    protected CameraFovInstruction readCameraFovInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraFovInstruction instruction = new CameraFovInstruction();
        instruction.setFieldOfView(buffer.readFloatLE());
        instruction.setFovEaseTime(buffer.readFloatLE());
        instruction.setFovEaseType(EasingFunction.from(buffer.readUnsignedByte()));
        instruction.setFieldOfViewClear(buffer.readBoolean());
        return instruction;
    }
}