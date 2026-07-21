package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class BiomeClimateData {
  private float temperature;

  private float downfall;

  private float snowAccumulationMin;

  private float snowAccumulationMax;
}
