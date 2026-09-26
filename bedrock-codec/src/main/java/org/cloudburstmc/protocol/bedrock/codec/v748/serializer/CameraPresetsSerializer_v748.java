package org.cloudburstmc.protocol.bedrock.codec.v748.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.CameraPresetsSerializer_v729;
import org.cloudburstmc.protocol.bedrock.data.camera.AudioListener;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraPresetsSerializer_v748 extends CameraPresetsSerializer_v729 {
    public static final CameraPresetsSerializer_v748 INSTANCE = new CameraPresetsSerializer_v748();

    @Override
    public void writeCameraPresets(ByteBuf buffer, BedrockCodecHelper helper, CameraPresets preset) {
        helper.writeString(buffer, preset.getName());
        helper.writeString(buffer, preset.getInheritFrom());
        helper.writeOptionalNull(buffer, preset.getPosX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getPosY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getPosZ(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getRotX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getRotY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getRotationSpeed(), ByteBuf::writeFloatLE);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, preset.getSnapToTarget(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
        helper.writeOptionalNull(buffer, preset.getHorizontalRotationLimit(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, preset.getVerticalRotationLimit(), helper::writeVector2f);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, preset.getContinueTargeting(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
        helper.writeOptionalNull(buffer, preset.getViewOffset(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, preset.getEntityOffset(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, preset.getRadius(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getListener(), (buf, listener) -> buf.writeByte(listener.ordinal()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, preset.getPlayerEffects(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, preset.getAlignTargetAndCameraForward(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
    }

    @Override
    public CameraPresets readCameraPresets(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraPresets preset = new CameraPresets();
        preset.setName(helper.readString(buffer));
        preset.setInheritFrom(helper.readString(buffer));
        preset.setPosX(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        preset.setPosY(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        preset.setPosZ(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        preset.setRotX(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        preset.setRotY(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        preset.setRotationSpeed(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        preset.setSnapToTarget(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        preset.setHorizontalRotationLimit(helper.readOptional(buffer, null, helper::readVector2f));
        preset.setVerticalRotationLimit(helper.readOptional(buffer, null, helper::readVector2f));
        preset.setContinueTargeting(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        preset.setViewOffset(helper.readOptional(buffer, null, helper::readVector2f));
        preset.setEntityOffset(helper.readOptional(buffer, null, helper::readVector3f));
        preset.setRadius(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        preset.setListener(helper.readOptional(buffer, null, buf -> AudioListener.from(buf.readUnsignedByte())));
        preset.setPlayerEffects(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        preset.setAlignTargetAndCameraForward(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        return preset;
    }
}
