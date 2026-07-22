package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Provides information about a cauldron use
 */
@Data
public class CauldronUsed {
  /**
   * Color of the cauldron contents, 0 if the contents aren't colorized
   */
  private int contentsColor;

  /**
   * Type of the contents, defaults to -1. Sends -2 for dyed water
   */
  private int contentsType;

  /**
   * Fill level of a cauldron, usually [0,6]
   */
  private int fillLevel;
}
