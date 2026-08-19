package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.CameraInstructionSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineType;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraInstructionSerializer_v924 extends CameraInstructionSerializer_v859 {
    public static final CameraInstructionSerializer_v924 INSTANCE = new CameraInstructionSerializer_v924();

    @Override
    protected void writeSplineInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineInstruction instruction) {
        buffer.writeFloatLE(instruction.getTotalTime());
        buffer.writeByte(instruction.getType().ordinal());
        helper.writeArray(buffer, instruction.getCurve(), helper::writeVector3f);
        helper.writeArray(buffer, instruction.getProgressKeyFrames(), (buf, h, option) -> {
            buf.writeFloatLE(option.getKeyFrameValue());
            buf.writeFloatLE(option.getKeyFrameTime());
            h.writeString(buf, option.getKeyFrameEasingFunc().getSerializeName());
        });
        helper.writeArray(buffer, instruction.getRotationOption(), (buf, h, option) -> {
            h.writeVector3f(buf, option.getKeyFrameValue());
            buf.writeFloatLE(option.getKeyFrameTime());
            h.writeString(buf, option.getKeyFrameEasingFunc().getSerializeName());
        });
        helper.writeString(buffer, instruction.getSplineIdentifier());
        buffer.writeBoolean(instruction.isLoadFromJSON());
    }

    @Override
    protected CameraSplineInstruction readSplineInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final float totalTime = buffer.readFloatLE();
        final CameraSplineType type = CameraSplineType.from(buffer.readUnsignedByte());
        final List<Vector3f> curve = new ObjectArrayList<>();
        helper.readArray(buffer, curve, helper::readVector3f);
        final List<CameraSplineInstruction.SplineProgressOption> progressKeyFrames = new ObjectArrayList<>();
        helper.readArray(buffer, progressKeyFrames, (buf, h) -> {
            final float keyFrameValue = buf.readFloatLE();
            final float keyFrameTime = buf.readFloatLE();
            final EasingType keyFrameEasingFunc = EasingType.fromName(h.readString(buf));
            return new CameraSplineInstruction.SplineProgressOption(keyFrameValue, keyFrameTime, keyFrameEasingFunc);
        });
        final List<CameraSplineInstruction.SplineRotationOption> rotationOption = new ObjectArrayList<>();
        helper.readArray(buffer, rotationOption, (buf, h) -> {
            final Vector3f keyFrameValue = h.readVector3f(buf);
            final float keyFrameTime = buf.readFloatLE();
            final EasingType keyFrameEasingFunc = EasingType.fromName(h.readString(buf));
            return new CameraSplineInstruction.SplineRotationOption(null, -1f, keyFrameValue, keyFrameTime, keyFrameEasingFunc);
        });
        final String splineIdentifier = helper.readStringMaxLen(buffer, 1024);
        final boolean loadFromJSON = buffer.readBoolean();
        return new CameraSplineInstruction(totalTime, type, curve, progressKeyFrames, rotationOption, splineIdentifier, loadFromJSON);
    }
}