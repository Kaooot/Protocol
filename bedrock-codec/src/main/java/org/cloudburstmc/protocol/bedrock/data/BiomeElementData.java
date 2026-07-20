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
  private float noiseFreqScale;

  private float noiseLowerBound;

  private float noiseUpperBound;

  private int heightMinType;

  private int heightMin;

  private int heightMaxType;

  private int heightMax;

  private BiomeSurfaceMaterialData adjustedMaterials;
}
