package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpawnSettings {
  private SpawnBiomeType spawnBiomeType;

  private String userDefinedBiomeName;

  private int dimension;
}
