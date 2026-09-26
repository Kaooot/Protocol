package org.cloudburstmc.protocol.bedrock.codec.v2192.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.CameraPresetsSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets;

public class CameraPresetsSerializer_v2192 extends CameraPresetsSerializer_v818 {

    public static final CameraPresetsSerializer_v2192 INSTANCE = new CameraPresetsSerializer_v2192();

    @Override
    public void writeCameraPresets(ByteBuf buffer, BedrockCodecHelper helper, CameraPresets cameraPresets) {
        super.writeCameraPresets(buffer, helper, cameraPresets);
        buffer.writeBoolean(cameraPresets.isApplyInheritedStartingRotation());
        helper.writeOptionalNull(buffer, cameraPresets.getStartingRotation(), helper::writeVector2f);
    }

    @Override
    public CameraPresets readCameraPresets(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraPresets cameraPresets = super.readCameraPresets(buffer, helper);
        cameraPresets.setApplyInheritedStartingRotation(buffer.readBoolean());
        cameraPresets.setStartingRotation(helper.readOptional(buffer, null, helper::readVector2f));
        return cameraPresets;
    }
}