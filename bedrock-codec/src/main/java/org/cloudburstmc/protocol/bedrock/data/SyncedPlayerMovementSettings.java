package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class SyncedPlayerMovementSettings {
  private int rewindHistorySize;

  private boolean serverAuthoritativeBlockBreaking;
}
