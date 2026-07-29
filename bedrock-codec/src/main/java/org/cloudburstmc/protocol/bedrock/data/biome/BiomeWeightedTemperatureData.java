package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Data;

@Data
public class BiomeWeightedTemperatureData {
  private int temperature;

  private int weight;
}
