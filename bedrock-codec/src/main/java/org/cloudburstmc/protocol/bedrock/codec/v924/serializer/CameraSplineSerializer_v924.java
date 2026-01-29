package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.*;
import org.cloudburstmc.protocol.bedrock.packet.CameraSplinePacket;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraSplineSerializer_v924 implements BedrockPacketSerializer<CameraSplinePacket> {
    public static final CameraSplineSerializer_v924 INSTANCE = new CameraSplineSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraSplinePacket packet) {
        helper.writeArray(buffer, packet.getCameraDataSplines(), this::writeCameraSplineDefinition);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraSplinePacket packet) {
        helper.readArray(buffer, packet.getCameraDataSplines(), this::readCameraSplineDefinition);
    }

    protected void writeCameraSplineDefinition(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineDefinition definition) {
        helper.writeString(buffer, definition.getName());
        buffer.writeFloatLE(definition.getTotalTime());
        helper.writeString(buffer, definition.getSplineType().name().toLowerCase());
        helper.writeArray(buffer, definition.getControlPoints(), (buf, codecHelper, cameraSplineControlPoint) -> {
            codecHelper.writeVector3f(buf, cameraSplineControlPoint.getPosition());
        });
        helper.writeArray(buffer, definition.getProgressKeyFrames(), this::writeCameraSplineProgressKeyFrame);
        helper.writeArray(buffer, definition.getRotationKeyFrames(), this::writeCameraSplineRotationKeyFrame);
    }

    private CameraSplineDefinition readCameraSplineDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final String name = helper.readString(buffer);
        final float totalTime = buffer.readFloatLE();
        final CameraSplineType splineType = CameraSplineType.valueOf(helper.readString(buffer).toUpperCase());
        final List<CameraSplineControlPoint> controlPoints = new ObjectArrayList<>();
        final List<CameraSplineProgressKeyFrame> progressKeyFrames = new ObjectArrayList<>();
        final List<CameraSplineRotationKeyFrame> rotationKeyFrames = new ObjectArrayList<>();
        helper.readArray(buffer, controlPoints, (buf, codecHelper) ->
                new CameraSplineControlPoint(codecHelper.readVector3f(buf)));
        helper.readArray(buffer, progressKeyFrames, this::readCameraSplineProgressKeyFrame);
        helper.readArray(buffer, rotationKeyFrames, this::readCameraSplineRotationKeyFrame);
        return new CameraSplineDefinition(
                name, totalTime, splineType, controlPoints, progressKeyFrames, rotationKeyFrames
        );
    }

    protected void writeCameraSplineProgressKeyFrame(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineProgressKeyFrame keyFrame) {
        buffer.writeFloatLE(keyFrame.getProgress());
        buffer.writeFloatLE(keyFrame.getTime());
        helper.writeString(buffer, keyFrame.getEasing().getSerializeName());
    }

    protected CameraSplineProgressKeyFrame readCameraSplineProgressKeyFrame(ByteBuf buffer, BedrockCodecHelper helper) {
        final float progress = buffer.readFloatLE();
        final float time = buffer.readFloatLE();
        final EasingType easing = EasingType.fromName(helper.readString(buffer));
        return new CameraSplineProgressKeyFrame(progress, time, easing);
    }

    protected void writeCameraSplineRotationKeyFrame(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineRotationKeyFrame keyFrame) {
        helper.writeVector3f(buffer, keyFrame.getRotation());
        buffer.writeFloatLE(keyFrame.getTime());
        helper.writeString(buffer, keyFrame.getEasing().getSerializeName());
    }

    protected CameraSplineRotationKeyFrame readCameraSplineRotationKeyFrame(ByteBuf buffer, BedrockCodecHelper helper) {
        final Vector3f rotation = helper.readVector3f(buffer);
        final float time = buffer.readFloatLE();
        final EasingType easing = EasingType.fromName(helper.readString(buffer));
        return new CameraSplineRotationKeyFrame(rotation, time, easing);
    }

    /*private void dump(ByteBuf buffer, int maxLength) {
        final ByteBuf copy = buffer.copy();
        final byte[] data = new byte[maxLength == -1 ? copy.readableBytes() : maxLength];
        copy.readBytes(data);
        System.out.println(ByteBufUtil.hexDump(data));
        System.out.println(Arrays.toString(data));
    }

    private void dump(ByteBuf buffer) {
        this.dump(buffer, -1);
    }*/
}