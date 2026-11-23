package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Provides information about a POI cauldron use
 */
@Data
@Added("1.11.0")
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