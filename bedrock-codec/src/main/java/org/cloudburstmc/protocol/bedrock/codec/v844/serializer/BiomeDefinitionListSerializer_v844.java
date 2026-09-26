package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v827.serializer.BiomeDefinitionListSerializer_v827;
import org.cloudburstmc.protocol.bedrock.data.biome.*;

import java.awt.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v844 extends BiomeDefinitionListSerializer_v827 {
    public static final BiomeDefinitionListSerializer_v844 INSTANCE = new BiomeDefinitionListSerializer_v844();

    protected void writeClimate(ByteBuf buffer, BedrockCodecHelper helper, BiomeClimateData climate) {
        buffer.writeFloatLE(climate.getTemperature());
        buffer.writeFloatLE(climate.getDownfall());
        buffer.writeFloatLE(climate.getSnowAccumulationMin());
        buffer.writeFloatLE(climate.getSnowAccumulationMax());
    }

    protected BiomeClimateData readClimate(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeClimateData data = new BiomeClimateData();
        data.setTemperature(buffer.readFloatLE());
        data.setDownfall(buffer.readFloatLE());
        data.setSnowAccumulationMin(buffer.readFloatLE());
        data.setSnowAccumulationMax(buffer.readFloatLE());
        return data;
    }

    protected void writeBiomeDefinitionData(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition) {
        this.writeDefinitionId(buffer, helper, definition);
        buffer.writeFloatLE(definition.getTemperature());
        buffer.writeFloatLE(definition.getDownfall());
        buffer.writeFloatLE(definition.getFoliageSnow());
        buffer.writeFloatLE(definition.getDepth());
        buffer.writeFloatLE(definition.getScale());
        buffer.writeIntLE(definition.getMapWaterColorArgb().getRGB());
        buffer.writeBoolean(definition.isRain());
        helper.writeOptionalNull(buffer, definition.getTags(), this::writeBiomeTagsData);
        helper.writeOptionalNull(buffer, definition.getChunkGenData(), this::writeBiomeDefinitionChunkGenData);
    }

    protected BiomeDefinitionData readBiomeDefinitionData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeDefinitionData data = new BiomeDefinitionData();
        data.setId(this.readDefinitionId(buffer, helper));
        data.setTemperature(buffer.readFloatLE());
        data.setDownfall(buffer.readFloatLE());
        data.setFoliageSnow(buffer.readFloatLE());
        data.setDepth(buffer.readFloatLE());
        data.setScale(buffer.readFloatLE());
        data.setMapWaterColorArgb(new Color(buffer.readIntLE(), true));
        data.setRain(buffer.readBoolean());
        data.setTags(helper.readOptional(buffer, null, this::readBiomeTagsData));
        data.setChunkGenData(helper.readOptional(buffer, null, this::readBiomeDefinitionChunkGenData));
        return data;
    }

    @Override
    protected void writeBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(), this::writeBiomeConsolidatedFeaturesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeBiomeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustments(), this::writeBiomeSurfaceMaterialAdjustmentData);
        this.writeBiomeSurfaceBuilderData(buffer, helper, definitionChunkGen.getSurfaceBuilderData());
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(), this::writeBiomeOverworldGenRulesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeBiomeMultinoiseGenRulesData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(), this::writeBiomeLegacyWorldGenRulesData);
    }

    @Override
    protected BiomeDefinitionChunkGenData readBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeDefinitionChunkGenData data = new BiomeDefinitionChunkGenData();
        data.setClimate(helper.readOptional(buffer, null, this::readClimate));
        data.setConsolidatedFeatures(helper.readOptional(buffer, null, this::readBiomeConsolidatedFeaturesData));
        data.setMountainParams(helper.readOptional(buffer, null, this::readBiomeMountainParamsData));
        data.setSurfaceMaterialAdjustments(helper.readOptional(buffer, null, this::readBiomeSurfaceMaterialAdjustmentData));
        data.setSurfaceBuilderData(this.readBiomeSurfaceBuilderData(buffer, helper));
        data.setOverworldGenRules(helper.readOptional(buffer, null, this::readBiomeOverworldGenRulesData));
        data.setMultinoiseGenRules(helper.readOptional(buffer, null, this::readBiomeMultinoiseGenRulesData));
        data.setLegacyWorldGenRules(helper.readOptional(buffer, null, this::readBiomeLegacyWorldGenRulesData));
        return data;
    }

    @Override
    protected void writeBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceBuilderData data) {
        helper.writeOptionalNull(buffer, data.getSurfaceMaterials(), this::writeBiomeSurfaceMaterialData);
        buffer.writeBoolean(data.isHasDefaultOverworldSurface());
        buffer.writeBoolean(data.isHasSwampSurface());
        buffer.writeBoolean(data.isHasFrozenOceanSurface());
        buffer.writeBoolean(data.isHasTheEndSurface());
        helper.writeOptionalNull(buffer, data.getMesaSurface(), this::writeBiomeMesaSurfaceData);
        helper.writeOptionalNull(buffer, data.getCappedSurface(), this::writeBiomeCappedSurfaceData);
    }

    @Override
    protected BiomeSurfaceBuilderData readBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeSurfaceBuilderData data = new BiomeSurfaceBuilderData();
        data.setSurfaceMaterials(helper.readOptional(buffer, null, this::readBiomeSurfaceMaterialData));
        data.setHasDefaultOverworldSurface(buffer.readBoolean());
        data.setHasSwampSurface(buffer.readBoolean());
        data.setHasFrozenOceanSurface(buffer.readBoolean());
        data.setHasTheEndSurface(buffer.readBoolean());
        data.setMesaSurface(helper.readOptional(buffer, null, this::readBiomeMesaSurfaceData));
        data.setCappedSurface(helper.readOptional(buffer, null, this::readBiomeCappedSurfaceData));
        return data;
    }
}