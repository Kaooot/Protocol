package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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

  private BiomeReplacementsData replacementBiomes;

  private VillageType villageType;

  private BiomeSurfaceBuilderData surfaceBuilderData;

  private BiomeSurfaceBuilderData subsurfaceBuilderData;
}
