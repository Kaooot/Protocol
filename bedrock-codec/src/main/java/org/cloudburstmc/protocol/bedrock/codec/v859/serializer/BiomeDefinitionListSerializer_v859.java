package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v844.serializer.BiomeDefinitionListSerializer_v844;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.common.util.SequencedHashSet;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.common.util.index.Indexed;
import org.cloudburstmc.protocol.common.util.index.IndexedList;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v859 extends BiomeDefinitionListSerializer_v844 {
    public static final BiomeDefinitionListSerializer_v859 INSTANCE = new BiomeDefinitionListSerializer_v859();

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen, SequencedHashSet<String> strings) {
        super.writeDefinitionChunkGen(buffer, helper, definitionChunkGen, strings);
        helper.writeOptionalNull(buffer, definitionChunkGen.getReplacementData(), (buf, codecHelper, data) -> this.writeBiomeReplacement(buf, codecHelper, data, strings));
    }

    @Override
    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readConsolidatedFeatures(buf, aHelper, strings));
        BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readSurfaceMaterialAdjustment(buf, aHelper, strings));
        BiomeSurfaceMaterialData surfaceMaterial = helper.readOptional(buffer, null, this::readSurfaceMaterial);
        boolean hasDefaultOverworldSurface = buffer.readBoolean();
        boolean hasSwampSurface = buffer.readBoolean();
        boolean hasFrozenOceanSurface = buffer.readBoolean();
        boolean hasTheEndSurface = buffer.readBoolean();
        BiomeMesaSurfaceData mesaSurface = helper.readOptional(buffer, null, this::readMesaSurface);
        BiomeCappedSurfaceData cappedSurface = helper.readOptional(buffer, null, this::readCappedSurface);
        BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readOverworldGenRules(buf, aHelper, strings));
        BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readLegacyWorldGenRules(buf, aHelper, strings));
        BiomeReplacementData replacementData = helper.readOptional(buffer, null, (buf, codecHelper) -> this.readBiomeReplacement(buffer, codecHelper, strings));

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceMaterial, hasDefaultOverworldSurface, hasSwampSurface,
                hasFrozenOceanSurface, hasTheEndSurface,
                mesaSurface, cappedSurface,
                overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, replacementData, null);
    }

    protected void writeBiomeReplacement(ByteBuf buffer, BedrockCodecHelper helper, BiomeReplacementData data, SequencedHashSet<String> strings) {
        buffer.writeShortLE(strings.addAndGetIndex(data.getBiome()));
        buffer.writeShortLE(strings.addAndGetIndex(data.getDimension()));
        helper.writeArray(buffer, data.getTargetBiomes(), (buf, value) -> buf.writeShortLE(strings.addAndGetIndex(value)));
        buffer.writeFloatLE(data.getAmount());
        buffer.writeFloatLE(data.getNoiseFrequencyScale());
        buffer.writeIntLE(data.getReplacementIndex());
    }

    protected BiomeReplacementData readBiomeReplacement(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        final Indexed<String> biome = new Indexed<>(strings, buffer.readShortLE());
        final Indexed<String> dimension = new Indexed<>(strings, buffer.readShortLE());
        final int targetBiomesLength = VarInts.readUnsignedInt(buffer);
        final int[] targetBiomesIndices = new int[targetBiomesLength];
        for (int i = 0; i < targetBiomesLength; i++) {
            targetBiomesIndices[i] = buffer.readShortLE();
        }
        final IndexedList<String> targetBiomes = new IndexedList<>(strings, targetBiomesIndices);
        final float amount = buffer.readFloatLE();
        final float noiseFrequencyScale = buffer.readFloatLE();
        final int replacementIndex = buffer.readIntLE();
        return new BiomeReplacementData(biome, dimension, targetBiomes, amount, noiseFrequencyScale, replacementIndex);
    }
}