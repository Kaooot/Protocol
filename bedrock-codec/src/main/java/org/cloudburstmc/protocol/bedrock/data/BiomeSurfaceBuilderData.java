package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
