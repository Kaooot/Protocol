package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Sent on target block hit
 */
@Data
@Added("1.17.40")
public class TargetBlockHit {

    /**
     * Redstone signal level emanating from the target block
     */
    private int redstoneLevel;
}