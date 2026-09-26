package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.CameraSplineSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineDefinition;

public class CameraSplineSerializer_v944 extends CameraSplineSerializer_v924 {

    public static final CameraSplineSerializer_v944 INSTANCE = new CameraSplineSerializer_v944();

    @Override
    protected void writeCameraSplineDefinition(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineDefinition definition) {
        super.writeCameraSplineDefinition(buffer, helper, definition);
        helper.writeString(buffer, definition.getSplineIdentifier());
        buffer.writeBoolean(definition.isLoadFromJson());
    }

    @Override
    protected CameraSplineDefinition readCameraSplineDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSplineDefinition definition = super.readCameraSplineDefinition(buffer, helper);
        definition.setSplineIdentifier(helper.readString(buffer));
        definition.setLoadFromJson(buffer.readBoolean());
        return definition;
    }
}