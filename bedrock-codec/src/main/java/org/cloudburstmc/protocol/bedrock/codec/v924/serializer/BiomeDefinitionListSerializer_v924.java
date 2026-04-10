package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.BiomeDefinitionListSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.biome.*;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v924 extends BiomeDefinitionListSerializer_v859 {
    public static final BiomeDefinitionListSerializer_v924 INSTANCE = new BiomeDefinitionListSerializer_v924();

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        super.writeDefinitionChunkGen(buffer, helper, definitionChunkGen);
        helper.writeOptionalNull(buffer, definitionChunkGen.getVillageType(), (byteBuf, villageType) -> byteBuf.writeByte(villageType.ordinal()));
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
        final BiomeReplacementData replacementData = helper.readOptional(buffer, null, (buf, codecHelper) -> this.readBiomeReplacement(buffer, codecHelper));
        final VillageType villageType = helper.readOptional(buffer, null, (byteBuf, codecHelper) -> VillageType.from(byteBuf.readUnsignedByte()));

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData,
                overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, replacementData, null, villageType, null);
    }
}
