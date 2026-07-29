package org.cloudburstmc.protocol.bedrock.codec.v2177.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.CameraPresetsSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPreset;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraPresetsSerializer_v2177 extends CameraPresetsSerializer_v818 {
    public static final CameraPresetsSerializer_v2177 INSTANCE = new CameraPresetsSerializer_v2177();

    @Override
    public void writePreset(ByteBuf buffer, BedrockCodecHelper helper, CameraPreset preset) {
        super.writePreset(buffer, helper, preset);
        buffer.writeBoolean(preset.getApplyInheritedStartingRotation());
        helper.writeOptionalNull(buffer, preset.getStartingRotation(), helper::writeVector2f);
    }

    @Override
    public CameraPreset readPreset(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraPreset cameraPreset = super.readPreset(buffer, helper);
        cameraPreset.setApplyInheritedStartingRotation(buffer.readBoolean());
        cameraPreset.setStartingRotation(helper.readOptional(buffer, null, helper::readVector2f));
        return cameraPreset;
    }
}