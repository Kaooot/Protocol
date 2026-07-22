package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Sent on target block hit
 *
 * @since v471
 */
@Data
public class TargetBlockHit {
  /**
   * Redstone signal level emanating from the target block
   */
  private int redstoneLevel;
}
