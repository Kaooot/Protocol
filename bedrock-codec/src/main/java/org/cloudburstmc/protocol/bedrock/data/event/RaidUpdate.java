package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Sent as part of the LegacyTelemetryEventPacket to update the village raid data,
 * as soon as the boss bar is fully filled and a wave begins
 */
@Data
@Added("1.13.0")
public class RaidUpdate {

    /**
     * Current wave of the raid
     */
    private int currentWave;
    /**
     * Number of total waves
     */
    private int totalWaves;
    /**
     * Whether the raid was successfully completed
     */
    private boolean success;
}