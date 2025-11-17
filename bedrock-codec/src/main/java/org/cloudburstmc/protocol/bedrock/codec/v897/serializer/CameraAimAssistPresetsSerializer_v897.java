package org.cloudburstmc.protocol.bedrock.codec.v897.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v800.serializer.CameraAimAssistPresetsSerializer_v800;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistCategory;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistPresetDefinition;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraAimAssistPresetExclusionDefinition;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraAimAssistPresetsSerializer_v897 extends CameraAimAssistPresetsSerializer_v800 {
    public static final CameraAimAssistPresetsSerializer_v897 INSTANCE = new CameraAimAssistPresetsSerializer_v897();

    @Override
    protected void writeCategory(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistCategory category) {
        helper.writeString(buffer, category.getName());

        helper.writeArray(buffer, category.getEntities(), this::writePriority);
        helper.writeArray(buffer, category.getBlocks(), this::writePriority);
        helper.writeArray(buffer, category.getBlockTags(), this::writePriority);

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
        category.setEntityDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        category.setBlockDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        return category;
    }

    @Override
    protected void writePreset(ByteBuf buffer, BedrockCodecHelper helper,
                               CameraAimAssistPresetDefinition preset) {
        helper.writeString(buffer, preset.getIdentifier());
        this.writeExclusionSettings(buffer, helper, preset.getExclusionSettings());
        helper.writeArray(buffer, preset.getLiquidTargetingList(), helper::writeString);
        helper.writeArray(buffer, preset.getItemSettings(), this::writeItemSetting);
        helper.writeOptionalNull(buffer, preset.getDefaultItemSettings(), helper::writeString);
        helper.writeOptionalNull(buffer, preset.getHandSettings(), helper::writeString);
    }

    @Override
    protected CameraAimAssistPresetDefinition readPreset(ByteBuf buffer,
                                                         BedrockCodecHelper helper) {
        final CameraAimAssistPresetDefinition preset = new CameraAimAssistPresetDefinition();
        preset.setIdentifier(helper.readString(buffer));
        preset.setExclusionSettings(this.readExclusionSettings(buffer, helper));
        helper.readArray(buffer, preset.getLiquidTargetingList(), helper::readString);
        helper.readArray(buffer, preset.getItemSettings(), this::readItemSetting);
        preset.setDefaultItemSettings(helper.readOptional(buffer, null, helper::readString));
        preset.setHandSettings(helper.readOptional(buffer, null, helper::readString));
        return preset;
    }

    protected void writeExclusionSettings(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetExclusionDefinition definition) {
        helper.writeArray(buffer, definition.getBlockExclusionList(), helper::writeString);
        helper.writeArray(buffer, definition.getActorExclusionList(), helper::writeString);
        helper.writeArray(buffer, definition.getBlockTagExclusionList(), helper::writeString);
    }

    protected CameraAimAssistPresetExclusionDefinition readExclusionSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistPresetExclusionDefinition definition = new CameraAimAssistPresetExclusionDefinition();
        helper.readArray(buffer, definition.getActorExclusionList(), helper::readString);
        helper.readArray(buffer, definition.getBlockExclusionList(), helper::readString);
        helper.readArray(buffer, definition.getBlockTagExclusionList(), helper::readString);
        return definition;
    }
}