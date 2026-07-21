package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeMultinoiseGenRulesData {
  private float temperature;

  private float humidity;

  private float altitude;

  private float weirdness;

  private float weight;
}
