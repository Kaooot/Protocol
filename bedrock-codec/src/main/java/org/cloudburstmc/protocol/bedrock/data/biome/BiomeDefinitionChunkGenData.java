package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

@Value
public class BiomeDefinitionChunkGenData {
    @Nullable
    BiomeClimateData climate;
    @Nullable
    List<BiomeConsolidatedFeatureData> consolidatedFeatures;
    @Nullable
    BiomeMountainParamsData mountainParams;
    @Nullable
    BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustment;
    @Nullable
    BiomeSurfaceBuilderData surfaceBuilderData;
    @Nullable
    BiomeOverworldGenRulesData overworldGenRules;
    @Nullable
    BiomeMultinoiseGenRulesData multinoiseGenRules;
    @Nullable
    BiomeLegacyWorldGenRulesData legacyWorldGenRules;
    /**
     * @deprecated since v974
     */
    @Nullable
    BiomeReplacementData replacementData;
    /**
     * @since v974
     */
    @Nullable
    List<BiomeReplacementData> replacementBiomes;
    /**
     * @since v924
     */
    @Nullable
    VillageType villageType;
    /**
     * @since v974
     */
    @Nullable
    BiomeSurfaceBuilderData subSurfaceBuilderData;
}
