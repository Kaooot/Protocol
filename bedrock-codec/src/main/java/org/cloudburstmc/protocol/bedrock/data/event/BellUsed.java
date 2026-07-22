package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Provides information about a bell use
 *
 * @since v354
 */
@Data
public class BellUsed {
  /**
   * The runtime identifier of the used item
   */
  private int itemId;
}
