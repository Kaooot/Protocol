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
public class BiomeElementData {
  private float noisefreqscale;

  private float noiselowerbound;

  private float noiseupperbound;

  private int heightmintype;

  private int heightmin;

  private int heightmaxtype;

  private int heightmax;

  private BiomeSurfaceMaterialData adjustedmaterials;
}
