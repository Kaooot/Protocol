package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Piglin Barter telemetry data
 */
@Data
@Added("1.17.40")
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