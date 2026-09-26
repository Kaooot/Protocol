package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v800.serializer.CameraAimAssistPresetsSerializer_v800;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCategoryDefinition;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetExclusionDefinition;

public class CameraAimAssistPresetsSerializer_v898 extends CameraAimAssistPresetsSerializer_v800 {

    public static final CameraAimAssistPresetsSerializer_v898 INSTANCE = new CameraAimAssistPresetsSerializer_v898();

    @Override
    protected void writeCategory(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistCategoryDefinition category) {
        helper.writeString(buffer, category.getName());
        this.writePriority(buffer, helper, category.getPriorities().getEntities());
        this.writePriority(buffer, helper, category.getPriorities().getBlocks());
        this.writePriority(buffer, helper, category.getPriorities().getBlockTags());
        helper.writeOptionalNull(buffer, category.getPriorities().getEntityDefault(), ByteBuf::writeIntLE);
        helper.writeOptionalNull(buffer, category.getPriorities().getBlockDefault(), ByteBuf::writeIntLE);
    }

    @Override
    protected CameraAimAssistCategoryDefinition readCategory(ByteBuf buffer, BedrockCodecHelper helper) {
        CameraAimAssistCategoryDefinition category = new CameraAimAssistCategoryDefinition();
        category.setName(helper.readString(buffer));
        category.getPriorities().getEntities().putAll(this.readPriority(buffer, helper));
        category.getPriorities().getBlocks().putAll(this.readPriority(buffer, helper));
        category.getPriorities().getBlockTags().putAll(this.readPriority(buffer, helper));
        category.getPriorities().setEntityDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        category.getPriorities().setBlockDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        return category;
    }

    @Override
    protected void writeCameraAimAssistPresetExclusionDefinition(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetExclusionDefinition definition) {
        helper.writeArray(buffer, definition.getBlocks(), helper::writeString);
        helper.writeArray(buffer, definition.getEntities(), helper::writeString);
        helper.writeArray(buffer, definition.getBlockTags(), helper::writeString);
    }

    @Override
    protected CameraAimAssistPresetExclusionDefinition readCameraAimAssistPresetExclusionDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistPresetExclusionDefinition definition = new CameraAimAssistPresetExclusionDefinition();
        helper.readArray(buffer, definition.getBlocks(), helper::readString);
        helper.readArray(buffer, definition.getEntities(), helper::readString);
        helper.readArray(buffer, definition.getBlockTags(), helper::readString);
        return definition;
    }
}