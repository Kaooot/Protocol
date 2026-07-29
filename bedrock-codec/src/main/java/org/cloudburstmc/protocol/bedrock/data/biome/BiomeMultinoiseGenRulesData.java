package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Data;

@Data
public class BiomeMultinoiseGenRulesData {
  private float temperature;

  private float humidity;

  private float altitude;

  private float weirdness;

  private float weight;
}
