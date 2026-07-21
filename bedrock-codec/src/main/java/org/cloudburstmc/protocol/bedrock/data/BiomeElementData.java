package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
