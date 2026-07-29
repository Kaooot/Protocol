package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Data;

@Data
public class BiomeClimateData {
  private float temperature;

  private float downfall;

  private float snowAccumulationMin;

  private float snowAccumulationMax;
}
