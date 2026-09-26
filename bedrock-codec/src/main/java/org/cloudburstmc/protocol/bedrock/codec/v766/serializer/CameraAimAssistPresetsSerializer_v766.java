package org.cloudburstmc.protocol.bedrock.codec.v766.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.*;
import org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistPresetsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraAimAssistPresetsSerializer_v766 implements BedrockPacketSerializer<CameraAimAssistPresetsPacket> {
    public static final CameraAimAssistPresetsSerializer_v766 INSTANCE = new CameraAimAssistPresetsSerializer_v766();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetsPacket packet) {
        helper.writeArray(buffer, packet.getCameraAimAssistCategoriesDeprecated(), this::writeCategories);
        helper.writeArray(buffer, packet.getCameraAimAssistPresets(), this::writePreset);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetsPacket packet) {
        helper.readArray(buffer, packet.getCameraAimAssistCategoriesDeprecated(), this::readCategories);
        helper.readArray(buffer, packet.getCameraAimAssistPresets(), this::readPreset);
    }

    protected void writeCategories(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistCategories categories) {
        helper.writeString(buffer, categories.getIdentifier());
        helper.writeArray(buffer, categories.getCategories(), this::writeCategory);
    }

    protected CameraAimAssistCategories readCategories(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistCategories categories = new CameraAimAssistCategories(
                helper.readString(buffer)
        );
        helper.readArray(buffer, categories.getCategories(), this::readCategory);
        return categories;
    }

    protected void writeCategory(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistCategoryDefinition category) {
        helper.writeString(buffer, category.getName());
        this.writePriority(buffer, helper, category.getPriorities().getEntities());
        this.writePriority(buffer, helper, category.getPriorities().getBlocks());
        helper.writeOptionalNull(buffer, category.getPriorities().getEntityDefault(), ByteBuf::writeIntLE);
        helper.writeOptionalNull(buffer, category.getPriorities().getBlockDefault(), ByteBuf::writeIntLE);
    }

    protected CameraAimAssistCategoryDefinition readCategory(ByteBuf buffer, BedrockCodecHelper helper) {
        CameraAimAssistCategoryDefinition category = new CameraAimAssistCategoryDefinition();
        category.setName(helper.readString(buffer));
        category.getPriorities().getEntities().putAll(this.readPriority(buffer, helper));
        category.getPriorities().getBlocks().putAll(this.readPriority(buffer, helper));
        category.getPriorities().setEntityDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        category.getPriorities().setBlockDefault(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        return category;
    }

    protected void writePreset(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetDefinition preset) {
        helper.writeString(buffer, preset.getIdentifier());
        helper.writeString(buffer, preset.getCategories());
        this.writeCameraAimAssistPresetExclusionDefinition(buffer, helper, preset.getExclusionSettings());
        helper.writeArray(buffer, preset.getLiquidTargetingList(), helper::writeString);
        helper.writeArray(buffer, preset.getItemSettings(), this::writeItemSettings);
        helper.writeOptionalNull(buffer, preset.getDefaultItemSettings(), helper::writeString);
        helper.writeOptionalNull(buffer, preset.getHandSettings(), helper::writeString);
    }

    protected CameraAimAssistPresetDefinition readPreset(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistPresetDefinition preset = new CameraAimAssistPresetDefinition();
        preset.setIdentifier(helper.readString(buffer));
        preset.setCategories(helper.readString(buffer));
        preset.setExclusionSettings(this.readCameraAimAssistPresetExclusionDefinition(buffer, helper));
        helper.readArray(buffer, preset.getLiquidTargetingList(), helper::readString);
        helper.readArray(buffer, preset.getItemSettings(), this::readItemSettings);
        preset.setDefaultItemSettings(helper.readOptional(buffer, null, helper::readString));
        preset.setHandSettings(helper.readOptional(buffer, null, helper::readString));
        return preset;
    }

    protected void writePriority(ByteBuf buffer, BedrockCodecHelper helper, Map<String, Integer> priority) {
        VarInts.writeUnsignedInt(buffer, priority.size());
        for (Map.Entry<String, Integer> entry : priority.entrySet()) {
            helper.writeString(buffer, entry.getKey());
            buffer.writeIntLE(entry.getValue());
        }
    }

    protected Map<String, Integer> readPriority(ByteBuf buffer, BedrockCodecHelper helper) {
        final Map<String, Integer> map = new Object2IntArrayMap<>();
        final int size = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < size; i++) {
            map.put(helper.readString(buffer), buffer.readIntLE());
        }
        return map;
    }

    protected void writeItemSettings(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistItemSettings settings) {
        helper.writeString(buffer, settings.getItemId());
        helper.writeString(buffer, settings.getCategory());
    }

    protected CameraAimAssistItemSettings readItemSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        return new CameraAimAssistItemSettings(helper.readString(buffer), helper.readString(buffer));
    }

    protected void writeCameraAimAssistPresetExclusionDefinition(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetExclusionDefinition definition) {
        helper.writeArray(buffer, definition.getBlocks(), helper::writeString);
    }

    protected CameraAimAssistPresetExclusionDefinition readCameraAimAssistPresetExclusionDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistPresetExclusionDefinition definition = new CameraAimAssistPresetExclusionDefinition();
        helper.readArray(buffer, definition.getBlocks(), helper::readString);
        return definition;
    }
}