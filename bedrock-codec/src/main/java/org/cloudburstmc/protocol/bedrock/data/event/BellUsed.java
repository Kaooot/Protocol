package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Provides information about a bell use
 */
@Data
@Added("1.11.0")
public class BellUsed {

    /**
     * The runtime identifier of the used item
     */
    private int itemId;
}