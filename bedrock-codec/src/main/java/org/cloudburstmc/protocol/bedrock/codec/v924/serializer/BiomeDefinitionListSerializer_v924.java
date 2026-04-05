package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.BiomeDefinitionListSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.biome.*;
import org.cloudburstmc.protocol.common.util.SequencedHashSet;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v924 extends BiomeDefinitionListSerializer_v859 {
    public static final BiomeDefinitionListSerializer_v924 INSTANCE = new BiomeDefinitionListSerializer_v924();

    @Override
    protected void writeDefinitionChunkGen(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen, SequencedHashSet<String> strings) {
        super.writeDefinitionChunkGen(buffer, helper, definitionChunkGen, strings);
        helper.writeOptionalNull(buffer, definitionChunkGen.getVillageType(), (byteBuf, villageType) -> byteBuf.writeByte(villageType.ordinal()));
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
        BiomeReplacementData replacementData = helper.readOptional(buffer, null, this::readBiomeReplacement);
        VillageType villageType = helper.readOptional(buffer, null, (byteBuf, codecHelper) -> VillageType.from(byteBuf.readUnsignedByte()));

        return new BiomeDefinitionChunkGenData(climate, consolidatedFeatures,
                mountainParams, surfaceMaterialAdjustment,
                surfaceBuilderData,
                overworldGenRules, multinoiseGenRules,
                legacyWorldGenRules, replacementData, null, villageType, null);
    }
}
