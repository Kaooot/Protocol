package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncedPlayerMovementSettings {
  private int rewindHistorySize;

  private boolean serverAuthoritativeBlockBreaking;
}
