package org.cloudburstmc.protocol.bedrock.codec.v618.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.AudioListener;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets;
import org.cloudburstmc.protocol.bedrock.packet.CameraPresetsPacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraPresetsSerializer_v618 implements BedrockPacketSerializer<CameraPresetsPacket> {
    public static final CameraPresetsSerializer_v618 INSTANCE = new CameraPresetsSerializer_v618();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraPresetsPacket packet) {
        helper.writeArray(buffer, packet.getCameraPresets(), this::writeCameraPresets);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraPresetsPacket packet) {
        helper.readArray(buffer, packet.getCameraPresets(), this::readCameraPresets);
    }

    public void writeCameraPresets(ByteBuf buffer, BedrockCodecHelper helper, CameraPresets cameraPresets) {
        helper.writeString(buffer, cameraPresets.getName());
        helper.writeString(buffer, cameraPresets.getInheritFrom());
        helper.writeOptionalNull(buffer, cameraPresets.getPosX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getPosY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getPosZ(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getRotX(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getRotY(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, cameraPresets.getListener(), (buf, listener) -> buf.writeByte(listener.ordinal()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, cameraPresets.getPlayerEffects(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
    }

    public CameraPresets readCameraPresets(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraPresets cameraPresets = new CameraPresets();
        cameraPresets.setName(helper.readString(buffer));
        cameraPresets.setInheritFrom(helper.readString(buffer));
        cameraPresets.setPosX(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setPosY(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setPosZ(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setRotX(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setRotY(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        cameraPresets.setListener(AudioListener.from(buffer.readUnsignedByte()));
        cameraPresets.setPlayerEffects(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        return cameraPresets;
    }
}