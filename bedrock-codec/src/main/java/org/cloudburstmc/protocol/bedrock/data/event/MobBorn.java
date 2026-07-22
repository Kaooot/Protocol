package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Sent as soon as a new mob is born
 *
 * @since v332
 */
@Data
public class MobBorn {
  /**
   * The ActorType of the mob
   */
  private int bornBabyEntityType;

  /**
   * Actor variant
   */
  private int bornBabyEntityVariant;

  /**
   * Color of the born mob
   */
  private int bornBabyColor;
}
