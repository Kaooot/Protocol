package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.CameraAimAssistPresetsSerializer_v898;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistCategory;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistPresetExclusionDefinition;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraAimAssistPresetsSerializer_v924 extends CameraAimAssistPresetsSerializer_v898 {
    public static final CameraAimAssistPresetsSerializer_v924 INSTANCE = new CameraAimAssistPresetsSerializer_v924();

    @Override
    protected void writeCategory(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistCategory category) {
        helper.writeString(buffer, category.getName());

        helper.writeArray(buffer, category.getEntities(), this::writePriority);
        helper.writeArray(buffer, category.getBlocks(), this::writePriority);
        helper.writeArray(buffer, category.getBlockTags(), this::writePriority);
        helper.writeArray(buffer, category.getEntityTypeFamilies(), this::writePriority);

        helper.writeOptionalNull(buffer, category.getEntityDefault(), ByteBuf::writeIntLE);
        helper.writeOptionalNull(buffer, category.getBlockDefault(), ByteBuf::writeIntLE);
    }

    @Override
    protected CameraAimAssistCategory readCategory(ByteBuf buffer, BedrockCodecHelper helper) {
        CameraAimAssistCategory category = new CameraAimAssistCategory();
        category.setName(helper.readString(buffer));
        helper.readArray(buffer, category.getEntities(), this::readPriority);
        helper.readArray(buffer, category.getBlocks(), this::readPriority);
        helper.readArray(buffer, category.getBlockTags(), this::readPriority);
        helper.readArray(buffer, category.getEntityTypeFamilies(), this::readPriority);
        category.setEntityDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        category.setBlockDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        return category;
    }

    @Override
    protected void writeExclusionSettings(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetExclusionDefinition definition) {
        super.writeExclusionSettings(buffer, helper, definition);
        helper.writeArray(buffer, definition.getEntityTypeFamilyExclusionList(), helper::writeString);
    }

    @Override
    protected CameraAimAssistPresetExclusionDefinition readExclusionSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistPresetExclusionDefinition definition = new CameraAimAssistPresetExclusionDefinition();
        helper.readArray(buffer, definition.getActorExclusionList(), helper::readString);
        helper.readArray(buffer, definition.getBlockExclusionList(), helper::readString);
        helper.readArray(buffer, definition.getBlockTagExclusionList(), helper::readString);
        helper.readArray(buffer, definition.getEntityTypeFamilyExclusionList(), helper::readString);
        return definition;
    }
}