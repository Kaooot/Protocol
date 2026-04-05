package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v844.serializer.BiomeDefinitionListSerializer_v844;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.common.util.SequencedHashSet;

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
        helper.writeOptionalNull(buffer, definitionChunkGen.getReplacementData(), this::writeBiomeReplacement);
    }

    @Override
    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, List<String> strings) {
        BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readConsolidatedFeatures(buf, aHelper, strings));
        BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readSurfaceMaterialAdjustment(buf, aHelper, strings));
        BiomeSurfaceBuilderData surfaceBuilderData = helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData);
        BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readOverworldGenRules(buf, aHelper, strings));
        BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null,
                (buf, aHelper) -> this.readLegacyWorldGenRules(buf, aHelper, strings));
        BiomeReplacementData replacementData = helper.readOptional(buffer, null, this::readBiomeReplacement);

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData,
                overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, replacementData, null, null, null);
    }

    protected void writeBiomeReplacement(ByteBuf buffer, BedrockCodecHelper helper, BiomeReplacementData data) {
        buffer.writeShortLE(data.getBiome());
        buffer.writeShortLE(data.getDimension());
        helper.writeArray(buffer, data.getTargetBiomes(), (buf, value) -> buf.writeShortLE(value));
        buffer.writeFloatLE(data.getAmount());
        buffer.writeFloatLE(data.getNoiseFrequencyScale());
        buffer.writeIntLE(data.getReplacementIndex());
    }

    protected BiomeReplacementData readBiomeReplacement(ByteBuf buffer, BedrockCodecHelper helper) {
        final int biome = buffer.readShortLE();
        final int dimension = buffer.readShortLE();
        final List<Short> targetBiomes = new ObjectArrayList<>();
        helper.readArray(buffer, targetBiomes, ByteBuf::readShortLE);
        final float amount = buffer.readFloatLE();
        final float noiseFrequencyScale = buffer.readFloatLE();
        final int replacementIndex = buffer.readIntLE();
        return new BiomeReplacementData(biome, dimension, targetBiomes, amount, noiseFrequencyScale, replacementIndex);
    }
}