package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobKilled {
  private long instigatorActorID;

  private long targetActorID;

  private ActorType instigatorsChildActorType;

  private int damageSource;

  private int tradeTier;

  private String traderName;
}
