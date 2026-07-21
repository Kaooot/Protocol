package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class PlayerDied {
  private int instigatorActorID;

  private int instigatorMobVariant;

  private int damageSource;

  private boolean diedInRaid;
}
