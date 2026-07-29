package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Data;

@Data
public class BiomeWeightedData {
  private int biomeIdentifier;

  private int weight;
}
