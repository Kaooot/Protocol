package org.cloudburstmc.protocol.bedrock.codec.v712.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v618.serializer.CameraPresetsSerializer_v618;
import org.cloudburstmc.protocol.bedrock.data.camera.AudioListener;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraPresetsSerializer_v712 extends CameraPresetsSerializer_v618 {
    public static final CameraPresetsSerializer_v712 INSTANCE = new CameraPresetsSerializer_v712();

    @Override
    public void writeCameraPresets(ByteBuf buffer, BedrockCodecHelper helper, CameraPresets cameraPresets) {
        helper.writeString(buffer, cameraPresets.getName());
        helper.writeString(buffer, cameraPresets.getInheritFrom());
        helper.writeOptionalNull(buffer, cameraPresets.getPosX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getPosY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getPosZ(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getRotX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getRotY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getViewOffset(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, cameraPresets.getRadius(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getListener(), (buf, listener) -> buf.writeByte(listener.ordinal()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, cameraPresets.getPlayerEffects(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
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
        cameraPresets.setViewOffset(helper.readOptional(buffer, null, helper::readVector2f));
        cameraPresets.setRadius(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setListener(AudioListener.from(buffer.readUnsignedByte()));
        cameraPresets.setPlayerEffects(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        return cameraPresets;
    }
}