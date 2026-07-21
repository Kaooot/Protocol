package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class BiomeWeightedTemperatureData {
  private int temperature;

  private int weight;
}
