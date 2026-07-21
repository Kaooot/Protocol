package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

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
