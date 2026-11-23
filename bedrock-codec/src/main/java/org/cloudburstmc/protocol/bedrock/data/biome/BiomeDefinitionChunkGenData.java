package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
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
    @Added("1.21.120")
    private BiomeReplacementsData replacementBiomes;
    @Added("26.0")
    private VillageType villageType;
    @Added("26.10")
    private BiomeSurfaceBuilderData surfaceBuilderData;
    @Added("26.10")
    private BiomeSurfaceBuilderData subsurfaceBuilderData;
}