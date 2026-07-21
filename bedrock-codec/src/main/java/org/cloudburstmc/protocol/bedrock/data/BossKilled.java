package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class BossKilled {
  private long bossActorID;

  private int partySize;

  private int bossType;
}
