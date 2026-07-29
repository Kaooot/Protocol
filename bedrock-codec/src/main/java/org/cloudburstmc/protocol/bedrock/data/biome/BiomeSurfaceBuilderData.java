package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Data;

@Data
public class BiomeSurfaceBuilderData {
  private BiomeSurfaceMaterialData surfaceMaterials;

  private boolean hasDefaultOverworldSurface;

  private boolean hasSwampSurface;

  private boolean hasFrozenOceanSurface;

  private boolean hasTheEndSurface;

  private BiomeMesaSurfaceData mesaSurface;

  private BiomeCappedSurfaceData cappedSurface;

  private BiomeNoiseGradientSurfaceData noiseGradientSurface;
}
