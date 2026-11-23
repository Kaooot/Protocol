package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Provides information about a composter use
 */
@Data
@Added("1.11.0")
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