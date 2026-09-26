package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Provides information about a composter use
 *
 * @since v354
 */
@Data
public class ComposterUsed {

    /**
     * see MinecraftEventing::POIBlockInteractionType
     */
    private POIBlockInteractionType blockInteractionType;
    /**
     * The runtime identifier of the used item
     */
    private int itemId;
}