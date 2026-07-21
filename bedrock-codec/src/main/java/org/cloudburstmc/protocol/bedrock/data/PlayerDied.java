package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDied {
  private int instigatorActorID;

  private int instigatorMobVariant;

  private int damageSource;

  private boolean diedInRaid;
}
