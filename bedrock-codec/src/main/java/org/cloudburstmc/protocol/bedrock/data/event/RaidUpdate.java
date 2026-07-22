package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Sent as part of the LegacyTelemetryEventPacket to update the village raid data,
 * as soon as the boss bar is fully filled and a wave begins
 *
 * @since v388
 */
@Data
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
