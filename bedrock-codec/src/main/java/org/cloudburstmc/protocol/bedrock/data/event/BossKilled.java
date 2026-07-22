package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Provides information about a boss death
 */
@Data
public class BossKilled {
  /**
   * ActorUniqueID of the defeated boss
   */
  private long bossActorID;

  /**
   * Presumably the number of players who helped defeat the boss
   */
  private int partySize;

  /**
   * ActorType of the defeated boss
   */
  private int bossType;
}
