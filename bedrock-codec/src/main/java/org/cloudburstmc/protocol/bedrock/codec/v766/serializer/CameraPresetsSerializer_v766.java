package org.cloudburstmc.protocol.bedrock.codec.v766.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.CameraPresetsSerializer_v729;
import org.cloudburstmc.protocol.bedrock.data.camera.AudioListener;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.AimAssistTargetMode;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCommandDefinition;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraPresetsSerializer_v766 extends CameraPresetsSerializer_v729 {
    public static final CameraPresetsSerializer_v766 INSTANCE = new CameraPresetsSerializer_v766();

    @Override
    public void writeCameraPresets(ByteBuf buffer, BedrockCodecHelper helper, CameraPresets cameraPresets) {
        helper.writeString(buffer, cameraPresets.getName());
        helper.writeString(buffer, cameraPresets.getInheritFrom());
        helper.writeOptionalNull(buffer, cameraPresets.getPosX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getPosY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getPosZ(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getRotX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getRotY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getRotationSpeed(), ByteBuf::writeFloatLE);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, cameraPresets.getSnapToTarget(),
                (b, optional) -> b.writeBoolean(optional.getAsBoolean()));
        helper.writeOptionalNull(buffer, cameraPresets.getHorizontalRotationLimit(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, cameraPresets.getVerticalRotationLimit(), helper::writeVector2f);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, cameraPresets.getContinueTargeting(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
        helper.writeOptionalNull(buffer, cameraPresets.getBlockListeningRadius(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getViewOffset(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, cameraPresets.getRadius(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getListener(), (buf, listener) -> buf.writeByte(listener.ordinal()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, cameraPresets.getPlayerEffects(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, cameraPresets.getAlignTargetAndCameraForward(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
        helper.writeOptionalNull(buffer, cameraPresets.getAimAssist(), this::writeCameraAimAssistCommandDefinition);
    }

    @Override
    public CameraPresets readCameraPresets(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraPresets cameraPresets = new CameraPresets();
        cameraPresets.setName(helper.readString(buffer));
        cameraPresets.setInheritFrom(helper.readString(buffer));
        cameraPresets.setPosX(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setPosY(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setPosZ(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setRotX(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setRotY(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setRotationSpeed(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setSnapToTarget(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        cameraPresets.setHorizontalRotationLimit(helper.readOptional(buffer, null, helper::readVector2f));
        cameraPresets.setVerticalRotationLimit(helper.readOptional(buffer, null, helper::readVector2f));
        cameraPresets.setContinueTargeting(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        cameraPresets.setBlockListeningRadius(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setViewOffset(helper.readOptional(buffer, null, helper::readVector2f));
        cameraPresets.setEntityOffset(helper.readOptional(buffer, null, helper::readVector3f));
        cameraPresets.setRadius(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setListener(AudioListener.from(buffer.readUnsignedByte()));
        cameraPresets.setPlayerEffects(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        cameraPresets.setAlignTargetAndCameraForward(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        cameraPresets.setAimAssist(helper.readOptional(buffer, null, this::readCameraAimAssistCommandDefinition));
        return cameraPresets;
    }

    protected void writeCameraAimAssistCommandDefinition(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistCommandDefinition definition) {
        helper.writeOptionalNull(buffer, definition.getPresetId(), helper::writeString);
        helper.writeOptionalNull(buffer, definition.getTargetMode(),
                (buf, codecHelper, targetMode) -> buf.writeIntLE(targetMode.ordinal()));
        helper.writeOptionalNull(buffer, definition.getViewAngle(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, definition.getDistance(), ByteBuf::writeFloatLE);
    }

    protected CameraAimAssistCommandDefinition readCameraAimAssistCommandDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistCommandDefinition definition = new CameraAimAssistCommandDefinition();
        definition.setPresetId(helper.readOptional(buffer, null, helper::readString));
        final Integer targetMode = helper.readOptional(buffer, null, ByteBuf::readIntLE);
        definition.setTargetMode(targetMode == null ? AimAssistTargetMode.ANGLE : AimAssistTargetMode.from(targetMode));
        definition.setViewAngle(helper.readOptional(buffer, null, helper::readVector2f));
        definition.setDistance(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        return definition;
    }
}