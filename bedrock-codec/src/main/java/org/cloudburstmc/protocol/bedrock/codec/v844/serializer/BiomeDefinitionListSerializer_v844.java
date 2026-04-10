package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v827.serializer.BiomeDefinitionListSerializer_v827;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.awt.*;
import java.util.List;

/**
 * @author Kaooot
 */
public class BiomeDefinitionListSerializer_v844 extends BiomeDefinitionListSerializer_v827 {
    public static final BiomeDefinitionListSerializer_v844 INSTANCE = new BiomeDefinitionListSerializer_v844();

    @Override
    protected void writeClimate(ByteBuf buffer, BedrockCodecHelper helper, BiomeClimateData climate) {
        buffer.writeFloatLE(climate.getTemperature());
        buffer.writeFloatLE(climate.getDownfall());
        buffer.writeFloatLE(climate.getSnowAccumulationMin());
        buffer.writeFloatLE(climate.getSnowAccumulationMax());
    }

    @Override
    protected BiomeClimateData readClimate(ByteBuf buffer, BedrockCodecHelper helper) {
        float temperature = buffer.readFloatLE();
        float downfall = buffer.readFloatLE();
        float snowAccumulationMin = buffer.readFloatLE();
        float snowAccumulationMax = buffer.readFloatLE();

        return new BiomeClimateData(temperature, downfall, 0f, 0f, 0f, 0f, snowAccumulationMin, snowAccumulationMax);
    }

    @Override
    protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition) {
        this.writeDefinitionId(buffer, helper, definition);
        buffer.writeFloatLE(definition.getTemperature());
        buffer.writeFloatLE(definition.getDownfall());
        buffer.writeFloatLE(definition.getFoliageSnow());
        buffer.writeFloatLE(definition.getDepth());
        buffer.writeFloatLE(definition.getScale());
        buffer.writeIntLE(definition.getMapWaterColor().getRGB());
        buffer.writeBoolean(definition.isRain());
        helper.writeOptionalNull(buffer, definition.getTags(), (byteBuf, aHelper, tags) -> {
            VarInts.writeUnsignedInt(byteBuf, tags.size());
            for (short tag : tags) {
                byteBuf.writeShortLE(tag);
            }
        });
        helper.writeOptionalNull(buffer, definition.getChunkGenData(), this::writeDefinitionChunkGen);
    }

    protected BiomeDefinitionData readDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final short id = this.readDefinitionId(buffer, helper);
        final float temperature = buffer.readFloatLE();
        final float downfall = buffer.readFloatLE();
        final float foliageSnow = buffer.readFloatLE();
        final float depth = buffer.readFloatLE();
        final float scale = buffer.readFloatLE();
        final Color mapWaterColor = new Color(buffer.readIntLE(), true);
        final boolean rain = buffer.readBoolean();
        final List<Short> tags = helper.readOptional(buffer, null, (buf, codecHelper) -> {
            final List<Short> list = new ObjectArrayList<>();
            codecHelper.readArray(buf, list, (buf1, codecHelper1) -> (short) buf1.readUnsignedShortLE());
            return list;
        });

        final BiomeDefinitionChunkGenData chunkGenData = helper.readOptional(buffer, null, this::readDefinitionChunkGen);
        return new BiomeDefinitionData(id, temperature, downfall, 0f, 0f, 0f, 0f, depth, scale, mapWaterColor,
                rain, tags, chunkGenData, foliageSnow);
    }

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(), this::writeConsolidatedFeatures);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustment(), this::writeSurfaceMaterialAdjustment);
        this.writeBiomeSurfaceBuilderData(buffer, helper, definitionChunkGen.getSurfaceBuilderData());
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(), this::writeOverworldGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeMultinoiseGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(), this::writeLegacyWorldGenRules);
    }

    @Override
    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        final List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null, this::readConsolidatedFeatures);
        final BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        final BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null, this::readSurfaceMaterialAdjustment);
        final BiomeSurfaceBuilderData surfaceBuilderData = this.readBiomeSurfaceBuilderData(buffer, helper);
        final BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null, this::readOverworldGenRules);
        final BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        final BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null, this::readLegacyWorldGenRules);
        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData,
                overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, null, null, null, null);
    }

    @Override
    protected void writeBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper, BiomeSurfaceBuilderData data) {
        helper.writeOptionalNull(buffer, data.getSurfaceMaterial(), this::writeSurfaceMaterial);
        buffer.writeBoolean(data.isHasDefaultOverworldSurface());
        buffer.writeBoolean(data.isHasSwampSurface());
        buffer.writeBoolean(data.isHasFrozenOceanSurface());
        buffer.writeBoolean(data.isHasTheEndSurface());
        helper.writeOptionalNull(buffer, data.getMesaSurface(), this::writeMesaSurface);
        helper.writeOptionalNull(buffer, data.getCappedSurface(), this::writeCappedSurface);
    }

    @Override
    protected BiomeSurfaceBuilderData readBiomeSurfaceBuilderData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeSurfaceMaterialData surfaceMaterial = helper.readOptional(buffer, null, this::readSurfaceMaterial);
        final boolean hasDefaultOverworldSurface = buffer.readBoolean();
        final boolean hasSwampSurface = buffer.readBoolean();
        final boolean hasFrozenOceanSurface = buffer.readBoolean();
        final boolean hasTheEndSurface = buffer.readBoolean();
        final BiomeMesaSurfaceData mesaSurface = helper.readOptional(buffer, null, this::readMesaSurface);
        final BiomeCappedSurfaceData cappedSurface = helper.readOptional(buffer, null, this::readCappedSurface);
        return new BiomeSurfaceBuilderData(
                surfaceMaterial,
                hasDefaultOverworldSurface,
                hasSwampSurface,
                hasFrozenOceanSurface,
                hasTheEndSurface,
                mesaSurface,
                cappedSurface,
                null
        );
    }
}