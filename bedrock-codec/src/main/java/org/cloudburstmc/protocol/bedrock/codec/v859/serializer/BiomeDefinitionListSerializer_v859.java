package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v844.serializer.BiomeDefinitionListSerializer_v844;
import org.cloudburstmc.protocol.bedrock.data.biome.*;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v859 extends BiomeDefinitionListSerializer_v844 {
    public static final BiomeDefinitionListSerializer_v859 INSTANCE = new BiomeDefinitionListSerializer_v859();

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        super.writeDefinitionChunkGen(buffer, helper, definitionChunkGen);
        helper.writeOptionalNull(buffer, definitionChunkGen.getReplacementData(), (buf, codecHelper, data) -> this.writeBiomeReplacement(buf, codecHelper, data));
    }

    @Override
    protected BiomeDefinitionChunkGenData readDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeClimateData climate = helper.readOptional(buffer, null, this::readClimate);
        final List<BiomeConsolidatedFeatureData> consolidatedFeatures = helper.readOptional(buffer, null,
                this::readConsolidatedFeatures);
        final BiomeMountainParamsData mountainParams = helper.readOptional(buffer, null, this::readMountainParamsData);
        final BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment = helper.readOptional(buffer, null,
                this::readSurfaceMaterialAdjustment);
        final BiomeSurfaceBuilderData surfaceBuilderData = helper.readOptional(buffer, null, this::readBiomeSurfaceBuilderData);
        final BiomeOverworldGenRulesData overworldGenRules = helper.readOptional(buffer, null,
                this::readOverworldGenRules);
        final BiomeMultinoiseGenRulesData multinoiseGenRules = helper.readOptional(buffer, null, this::readMultinoiseGenRules);
        final BiomeLegacyWorldGenRulesData legacyWorldGenRules = helper.readOptional(buffer, null,
                this::readLegacyWorldGenRules);
        final BiomeReplacementData replacementData = helper.readOptional(buffer, null, (buf, codecHelper) -> this.readBiomeReplacement(buffer, codecHelper));

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData,
                overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, replacementData, null, null, null);
    }

    protected void writeBiomeReplacement(ByteBuf buffer, BedrockCodecHelper helper, BiomeReplacementData data) {
        buffer.writeShortLE(data.getBiome());
        buffer.writeShortLE(data.getDimension());
        helper.writeArray(buffer, data.getTargetBiomes(), (buf, codecHelper, targetBiome) -> buf.writeShortLE(targetBiome));
        buffer.writeFloatLE(data.getAmount());
        buffer.writeFloatLE(data.getNoiseFrequencyScale());
        buffer.writeIntLE(data.getReplacementIndex());
    }

    protected BiomeReplacementData readBiomeReplacement(ByteBuf buffer, BedrockCodecHelper helper) {
        final short biome = buffer.readShortLE();
        final short dimension = buffer.readShortLE();
        final List<Short> targetBiomes = new ObjectArrayList<>();
        helper.readArray(buffer, targetBiomes, ByteBuf::readShortLE);
        final float amount = buffer.readFloatLE();
        final float noiseFrequencyScale = buffer.readFloatLE();
        final int replacementIndex = buffer.readIntLE();
        return new BiomeReplacementData(biome, dimension, targetBiomes, amount, noiseFrequencyScale, replacementIndex);
    }
}