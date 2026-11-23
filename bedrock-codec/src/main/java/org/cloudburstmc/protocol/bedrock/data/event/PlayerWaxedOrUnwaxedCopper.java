package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Used for telemetry data relating to copper block wax state changes
 */
@Data
@Added("1.17.40")
public class PlayerWaxedOrUnwaxedCopper {

    /**
     * Block runtime identifier of the copper block which has been waxed or unwaxed
     */
    private int playerWaxedOrUnwaxedCopperBlockID;
}