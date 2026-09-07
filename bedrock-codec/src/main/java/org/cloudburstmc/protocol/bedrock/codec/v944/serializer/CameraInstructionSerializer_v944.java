
package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.CameraInstructionSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.camera.instruction.CameraFovInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.spline.SplineProgressOption;
import org.cloudburstmc.protocol.bedrock.data.camera.spline.SplineRotationOption;

public class CameraInstructionSerializer_v944 extends CameraInstructionSerializer_v924 {

    public static final CameraInstructionSerializer_v944 INSTANCE = new CameraInstructionSerializer_v944();

    @Override
    protected void writeSplineProgressOption(ByteBuf buffer, BedrockCodecHelper helper, SplineProgressOption option) {
        buffer.writeFloatLE(option.getKeyFrameValue());
        buffer.writeFloatLE(option.getKeyFrameTime());
        helper.writeString(buffer, option.getKeyFrameEasingFunc().getSerializeName());
    }

    @Override
    protected SplineProgressOption readSplineProgressOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final SplineProgressOption option = new SplineProgressOption();
        option.setKeyFrameValue(buffer.readFloatLE());
        option.setKeyFrameTime(buffer.readFloatLE());
        option.setKeyFrameEasingFunc(EasingFunction.fromName(helper.readString(buffer)));
        return option;
    }

    @Override
    protected void writeSplineRotationOption(ByteBuf buffer, BedrockCodecHelper helper, SplineRotationOption option) {
        helper.writeVector3f(buffer, option.getKeyFrameValue());
        buffer.writeFloatLE(option.getKeyFrameTime());
        helper.writeString(buffer, option.getKeyFrameEasingFunc().getSerializeName());
    }

    @Override
    protected SplineRotationOption readSplineRotationOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final SplineRotationOption option = new SplineRotationOption();
        option.setKeyFrameValue(helper.readVector3f(buffer));
        option.setKeyFrameTime(buffer.readFloatLE());
        option.setKeyFrameEasingFunc(EasingFunction.fromName(helper.readString(buffer)));
        return option;
    }

    @Override
    protected void writeCameraFovInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraFovInstruction fovInstruction) {
        buffer.writeFloatLE(fovInstruction.getFieldOfView());
        buffer.writeFloatLE(fovInstruction.getFovEaseTime());
        helper.writeString(buffer, fovInstruction.getFovEaseType().getSerializeName());
        buffer.writeBoolean(fovInstruction.isFieldOfViewClear());
    }

    @Override
    protected CameraFovInstruction readCameraFovInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraFovInstruction instruction = new CameraFovInstruction();
        instruction.setFieldOfView(buffer.readFloatLE());
        instruction.setFovEaseTime(buffer.readFloatLE());
        instruction.setFovEaseType(EasingFunction.fromName(helper.readString(buffer)));
        instruction.setFieldOfViewClear(buffer.readBoolean());
        return instruction;
    }
}