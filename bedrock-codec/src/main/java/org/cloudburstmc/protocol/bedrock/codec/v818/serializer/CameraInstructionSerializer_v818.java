package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v748.serializer.CameraInstructionSerializer_v748;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSetInstruction;

public class CameraInstructionSerializer_v818 extends CameraInstructionSerializer_v748 {

    public static final CameraInstructionSerializer_v818 INSTANCE = new CameraInstructionSerializer_v818();

    @Override
    protected void writeCameraSetInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraSetInstruction instruction) {
        super.writeCameraSetInstruction(buffer, helper, instruction);
        buffer.writeBoolean(instruction.isRemoveIgnoreStartingValuesComponent());
    }

    @Override
    protected CameraSetInstruction readCameraSetInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSetInstruction instruction = super.readCameraSetInstruction(buffer, helper);
        instruction.setRemoveIgnoreStartingValuesComponent(buffer.readBoolean());
        return instruction;
    }
}