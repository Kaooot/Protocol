package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.CameraInstructionSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.camera.instruction.CameraSplineInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.spline.SplineProgressOption;
import org.cloudburstmc.protocol.bedrock.data.camera.spline.SplineRotationOption;

public class CameraInstructionSerializer_v924 extends CameraInstructionSerializer_v859 {

    public static final CameraInstructionSerializer_v924 INSTANCE = new CameraInstructionSerializer_v924();

    @Override
    protected void writeCameraSplineInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineInstruction instruction) {
        super.writeCameraSplineInstruction(buffer, helper, instruction);
        helper.writeString(buffer, instruction.getSplineIdentifier());
        buffer.writeBoolean(instruction.isLoadFromJson());
    }

    @Override
    protected CameraSplineInstruction readCameraSplineInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSplineInstruction instruction = super.readCameraSplineInstruction(buffer, helper);
        instruction.setSplineIdentifier(helper.readString(buffer));
        instruction.setLoadFromJson(buffer.readBoolean());
        return instruction;
    }

    @Override
    protected void writeSplineProgressOption(ByteBuf buffer, BedrockCodecHelper helper, SplineProgressOption option) {
        buffer.writeFloatLE(option.getKeyFrameValue());
        buffer.writeFloatLE(option.getKeyFrameTime());
        buffer.writeByte(option.getKeyFrameEasingFunc().ordinal());
    }

    @Override
    protected SplineProgressOption readSplineProgressOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final SplineProgressOption option = new SplineProgressOption();
        option.setKeyFrameValue(buffer.readFloatLE());
        option.setKeyFrameTime(buffer.readFloatLE());
        option.setKeyFrameEasingFunc(EasingFunction.from(buffer.readUnsignedByte()));
        return option;
    }

    @Override
    protected void writeSplineRotationOption(ByteBuf buffer, BedrockCodecHelper helper, SplineRotationOption option) {
        helper.writeVector3f(buffer, option.getKeyFrameValue());
        buffer.writeFloatLE(option.getKeyFrameTime());
        buffer.writeByte(option.getKeyFrameEasingFunc().ordinal());
    }

    @Override
    protected SplineRotationOption readSplineRotationOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final SplineRotationOption option = new SplineRotationOption();
        option.setKeyFrameValue(helper.readVector3f(buffer));
        option.setKeyFrameTime(buffer.readFloatLE());
        option.setKeyFrameEasingFunc(EasingFunction.from(buffer.readUnsignedByte()));
        return option;
    }
}