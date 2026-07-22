package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Piglin Barter telemetry data
 *
 * @since v471
 */
@Data
public class PiglinBarter {
  /**
   * Runtime identifier of the item
   */
  private int itemId;

  /**
   * Whether the Piglin was targeting the bartering player
   */
  private boolean wasTargetingBarteringPlayer;
}
