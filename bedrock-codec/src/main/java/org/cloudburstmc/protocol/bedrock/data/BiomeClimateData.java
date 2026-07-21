package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeClimateData {
  private float temperature;

  private float downfall;

  private float snowAccumulationMin;

  private float snowAccumulationMax;
}
