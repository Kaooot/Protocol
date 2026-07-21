package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class MobKilled {
  private long instigatorActorID;

  private long targetActorID;

  private ActorType instigatorsChildActorType;

  private int damageSource;

  private int tradeTier;

  private String traderName;
}
