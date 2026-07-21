package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class SpawnSettings {
  private SpawnBiomeType spawnBiomeType;

  private String userDefinedBiomeName;

  private int dimension;
}
