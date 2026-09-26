package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.world.VillageType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeDefinitionChunkGenData {

    private BiomeClimateData climate;
    private BiomeConsolidatedFeaturesData consolidatedFeatures;
    private BiomeMountainParamsData mountainParams;
    private BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustments;
    private BiomeOverworldGenRulesData overworldGenRules;
    private BiomeMultinoiseGenRulesData multinoiseGenRules;
    private BiomeLegacyWorldGenRulesData legacyWorldGenRules;
    /**
     * @since v859
     */
    private BiomeReplacementsData replacementBiomes;
    /**
     * @since v924
     */
    private VillageType villageType;
    /**
     * @since v975
     */
    private BiomeSurfaceBuilderData surfaceBuilderData;
    /**
     * @since v975
     */
    private BiomeSurfaceBuilderData subsurfaceBuilderData;
}