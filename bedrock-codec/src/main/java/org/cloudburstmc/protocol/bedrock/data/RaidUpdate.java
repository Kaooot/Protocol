package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class RaidUpdate {
  private int currentWave;

  private int totalWaves;

  private boolean success;
}
