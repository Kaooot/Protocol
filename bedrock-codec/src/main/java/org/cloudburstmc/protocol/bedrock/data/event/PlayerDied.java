package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Provides information about a player death
 */
@Data
public class PlayerDied {
  /**
   * ActorUniqueID of the instigator
   */
  private int instigatorActorID;

  /**
   * Mob variant of the instigator
   */
  private int instigatorMobVariant;

  /**
   * Damage source, TODO: add enum reference
   */
  private int damageSource;

  /**
   * Whether the player died whilst being in a village raid
   *
   * @since v389
   */
  private boolean diedInRaid;
}
