package org.cloudburstmc.protocol.bedrock.data.event;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorType;

/**
 * Provides information about a mob that has been killed
 */
@Data
public class MobKilled {
  /**
   * The instigator's ActorUniqueID
   */
  private long instigatorActorID;

  /**
   * ActorUniqueID of the mob
   */
  private long targetActorID;

  /**
   * The ActorType of the instigator's child
   */
  private ActorType instigatorsChildActorType;

  /**
   * The damage source, TODO: add enum reference
   */
  private int damageSource;

  /**
   * The trade tier, if the mob was a trading actor, otherwise -1
   */
  private int tradeTier;

  /**
   * The name localization key of the trading actor. If the mob wasn't a trading actor this is an empty string.
   */
  private String traderName;
}
