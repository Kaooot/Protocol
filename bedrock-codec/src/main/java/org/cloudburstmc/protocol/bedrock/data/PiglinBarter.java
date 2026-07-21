package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class PiglinBarter {
  private int itemId;

  private boolean wasTargetingBarteringPlayer;
}
