package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.*;
import org.cloudburstmc.protocol.bedrock.packet.CameraSplinePacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
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
        helper.writeString(buffer, definition.getSplineType().getSerializeName());
        helper.writeArray(buffer, definition.getControlPoints(), this::writeCameraSplineControlPoint);
        helper.writeArray(buffer, definition.getProgressKeyFrames(), this::writeCameraSplineProgressKeyFrame);
        helper.writeArray(buffer, definition.getRotationKeyFrames(), this::writeCameraSplineRotationKeyFrame);
    }

    protected CameraSplineDefinition readCameraSplineDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSplineDefinition definition = new CameraSplineDefinition();
        definition.setName(helper.readString(buffer));
        definition.setTotalTime(buffer.readFloatLE());
        definition.setSplineType(CameraSplineType.fromName(helper.readString(buffer)));
        helper.readArray(buffer, definition.getControlPoints(), this::readCameraSplineControlPoint);
        helper.readArray(buffer, definition.getProgressKeyFrames(), this::readCameraSplineProgressKeyFrame);
        helper.readArray(buffer, definition.getRotationKeyFrames(), this::readCameraSplineRotationKeyFrame);
        return definition;
    }

    protected void writeCameraSplineControlPoint(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineControlPoint controlPoint) {
        helper.writeVector3f(buffer, controlPoint.getPosition());
    }

    protected CameraSplineControlPoint readCameraSplineControlPoint(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSplineControlPoint controlPoint = new CameraSplineControlPoint();
        controlPoint.setPosition(helper.readVector3f(buffer));
        return controlPoint;
    }

    protected void writeCameraSplineProgressKeyFrame(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineProgressKeyFrame progressKeyFrame) {
        buffer.writeFloatLE(progressKeyFrame.getProgress());
        buffer.writeFloatLE(progressKeyFrame.getTime());
        helper.writeString(buffer, progressKeyFrame.getEasing().getSerializeName());
    }

    private CameraSplineProgressKeyFrame readCameraSplineProgressKeyFrame(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSplineProgressKeyFrame progressKeyFrame = new CameraSplineProgressKeyFrame();
        progressKeyFrame.setProgress(buffer.readFloatLE());
        progressKeyFrame.setTime(buffer.readFloatLE());
        progressKeyFrame.setEasing(EasingFunction.fromName(helper.readString(buffer)));
        return progressKeyFrame;
    }

    protected void writeCameraSplineRotationKeyFrame(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineRotationKeyFrame keyFrame) {
        helper.writeVector3f(buffer, keyFrame.getRotation());
        buffer.writeFloatLE(keyFrame.getTime());
        helper.writeString(buffer, keyFrame.getEasing().getSerializeName());
    }

    private CameraSplineRotationKeyFrame readCameraSplineRotationKeyFrame(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSplineRotationKeyFrame rotationKeyFrame = new CameraSplineRotationKeyFrame();
        rotationKeyFrame.setRotation(helper.readVector3f(buffer));
        rotationKeyFrame.setTime(buffer.readFloatLE());
        rotationKeyFrame.setEasing(EasingFunction.fromName(helper.readString(buffer)));
        return rotationKeyFrame;
    }
}