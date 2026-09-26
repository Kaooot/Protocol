package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.CameraPresetsSerializer_v776;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets;
import org.cloudburstmc.protocol.bedrock.data.player.ControlScheme;

public class CameraPresetsSerializer_v800 extends CameraPresetsSerializer_v776 {

    public static final CameraPresetsSerializer_v800 INSTANCE = new CameraPresetsSerializer_v800();

    @Override
    public void writeCameraPresets(ByteBuf buffer, BedrockCodecHelper helper, CameraPresets cameraPresets) {
        super.writeCameraPresets(buffer, helper, cameraPresets);
        helper.writeOptionalNull(buffer, cameraPresets.getControlScheme(), (buf, scheme) -> buf.writeByte(scheme.ordinal()));
    }

    @Override
    public CameraPresets readCameraPresets(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraPresets cameraPresets = super.readCameraPresets(buffer, helper);
        cameraPresets.setControlScheme(helper.readOptional(buffer, null,
                buf -> ControlScheme.from(buf.readUnsignedByte())));
        return cameraPresets;
    }
}
