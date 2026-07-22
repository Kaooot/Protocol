package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.POIBlockInteractionType;

/**
 * Provides information about a POI cauldron use
 *
 * @since v354
 */
@Data
public class POICauldronUsed {
  /**
   * see MinecraftEventing::POIBlockInteractionType
   */
  private POIBlockInteractionType blockInteractionType;

  /**
   * The runtime identifier of the used item
   */
  private int itemId;
}
