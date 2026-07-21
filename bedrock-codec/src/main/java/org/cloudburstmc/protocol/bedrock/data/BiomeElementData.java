package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
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
