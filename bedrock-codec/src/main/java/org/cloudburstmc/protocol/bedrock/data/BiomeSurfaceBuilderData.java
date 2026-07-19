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
public class BiomeSurfaceBuilderData {
  private BiomeSurfaceMaterialData surfacematerials;

  private boolean hasdefaultoverworldsurface;

  private boolean hasswampsurface;

  private boolean hasfrozenoceansurface;

  private boolean hastheendsurface;

  private BiomeMesaSurfaceData mesasurface;

  private BiomeCappedSurfaceData cappedsurface;

  private BiomeNoiseGradientSurfaceData noisegradientsurface;
}
