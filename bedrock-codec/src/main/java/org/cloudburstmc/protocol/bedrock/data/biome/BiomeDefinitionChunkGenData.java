package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.VillageType;

@Data
public class BiomeDefinitionChunkGenData {
  private BiomeClimateData climate;

  private BiomeConsolidatedFeaturesData consolidatedFeatures;

  private BiomeMountainParamsData mountainParams;

  private BiomeSurfaceMaterialAdjustmentData surfaceMaterialAdjustments;

  private BiomeOverworldGenRulesData overworldGenRules;

  private BiomeMultinoiseGenRulesData multinoiseGenRules;

  private BiomeLegacyWorldGenRulesData legacyWorldGenRules;

  private BiomeReplacementsData replacementBiomes;

  private VillageType villageType;

  private BiomeSurfaceBuilderData surfaceBuilderData;

  private BiomeSurfaceBuilderData subsurfaceBuilderData;
}
