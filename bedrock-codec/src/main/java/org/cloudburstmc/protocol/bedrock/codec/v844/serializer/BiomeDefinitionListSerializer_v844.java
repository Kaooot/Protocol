package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v827.serializer.BiomeDefinitionListSerializer_v827;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.SequencedHashSet;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.common.util.index.Indexed;
import org.cloudburstmc.protocol.common.util.index.IndexedList;

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
    protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionData definition, SequencedHashSet<String> strings) {
        this.writeDefinitionId(buffer, helper, definition, strings);
        buffer.writeFloatLE(definition.getTemperature());
        buffer.writeFloatLE(definition.getDownfall());
        buffer.writeFloatLE(definition.getFoliageSnow());
        buffer.writeFloatLE(definition.getDepth());
        buffer.writeFloatLE(definition.getScale());
        buffer.writeIntLE(definition.getMapWaterColor().getRGB());
        buffer.writeBoolean(definition.isRain());
        helper.writeOptionalNull(buffer, definition.getTags(), (byteBuf, aHelper, tags) -> {
            VarInts.writeUnsignedInt(byteBuf, tags.size());
            for (String tag : tags) {
                byteBuf.writeShortLE(strings.addAndGetIndex(tag));
            }
        });
        helper.writeOptionalNull(buffer, definition.getChunkGenData(),
                (buf, aHelper, data) -> writeDefinitionChunkGen(buf, aHelper, data, strings));
    }

    protected BiomeDefinitionData readDefinition(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        Indexed<String> id = this.readDefinitionId(buffer, helper, strings);
        float temperature = buffer.readFloatLE();
        float downfall = buffer.readFloatLE();
        float foliageSnow = buffer.readFloatLE();
        float depth = buffer.readFloatLE();
        float scale = buffer.readFloatLE();
        Color mapWaterColor = new Color(buffer.readIntLE(), true);
        boolean rain = buffer.readBoolean();


        IndexedList<String> tags = helper.readOptional(buffer, null, byteBuf -> {
            int length = VarInts.readUnsignedInt(byteBuf);
            Preconditions.checkArgument(byteBuf.isReadable(length * 2), "Not enough readable bytes for tags");
            int[] array = new int[length];
            for (int i = 0; i < length; i++) {
                array[i] = byteBuf.readUnsignedShortLE();
            }
            return new IndexedList<>(strings, array);
        });

        BiomeDefinitionChunkGenData chunkGenData = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readDefinitionChunkGen(buf, aHelper, strings));

        return new BiomeDefinitionData(id, temperature, downfall, 0f, 0f, 0f, 0f, depth, scale, mapWaterColor,
                rain, tags, chunkGenData, foliageSnow);
    }

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen,
                                           SequencedHashSet<String> strings) {
        helper.writeOptionalNull(buffer, definitionChunkGen.getClimate(), this::writeClimate);
        helper.writeOptionalNull(buffer, definitionChunkGen.getConsolidatedFeatures(),
                (buf, aHelper, consolidatedFeatures) -> this.writeConsolidatedFeatures(buf, aHelper, consolidatedFeatures, strings));
        helper.writeOptionalNull(buffer, definitionChunkGen.getMountainParams(), this::writeMountainParamsData);
        helper.writeOptionalNull(buffer, definitionChunkGen.getSurfaceMaterialAdjustment(),
                (buf, aHelper, surfaceMaterialAdjustment) -> this.writeSurfaceMaterialAdjustment(buf, aHelper, surfaceMaterialAdjustment, strings));
        this.writeBiomeSurfaceBuilderData(buffer, helper, definitionChunkGen.getSurfaceBuilderData());
        helper.writeOptionalNull(buffer, definitionChunkGen.getOverworldGenRules(),
                (buf, aHelper, overworldGenRules) -> this.writeOverworldGenRules(buf, aHelper, overworldGenRules, strings));
        helper.writeOptionalNull(buffer, definitionChunkGen.getMultinoiseGenRules(), this::writeMultinoiseGenRules);
        helper.writeOptionalNull(buffer, definitionChunkGen.getLegacyWorldGenRules(),
                (buf, aHelper, legacyWorldGenRules) -> this.writeLegacyWorldGenRules(buf, aHelper, legacyWorldGenRules, strings));
    }

    @Override
    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readConsolidatedFeatures(buf, aHelper, strings));
        BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readSurfaceMaterialAdjustment(buf, aHelper, strings));
        BiomeSurfaceBuilderData surfaceBuilderData = this.readBiomeSurfaceBuilderData(buffer, helper);
        BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readOverworldGenRules(buf, aHelper, strings));
        BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readLegacyWorldGenRules(buf, aHelper, strings));
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
        BiomeSurfaceMaterialData surfaceMaterial = helper.readOptional(buffer, null, this::readSurfaceMaterial);
        boolean hasDefaultOverworldSurface = buffer.readBoolean();
        boolean hasSwampSurface = buffer.readBoolean();
        boolean hasFrozenOceanSurface = buffer.readBoolean();
        boolean hasTheEndSurface = buffer.readBoolean();
        BiomeMesaSurfaceData mesaSurface = helper.readOptional(buffer, null, this::readMesaSurface);
        BiomeCappedSurfaceData cappedSurface = helper.readOptional(buffer, null, this::readCappedSurface);
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