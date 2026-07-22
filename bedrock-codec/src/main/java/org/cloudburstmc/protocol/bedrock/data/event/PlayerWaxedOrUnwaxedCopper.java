package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Used for telemetry data relating to copper block wax state changes
 *
 * @since v471
 */
@Data
public class PlayerWaxedOrUnwaxedCopper {
  /**
   * Block runtime identifier of the copper block which has been waxed or unwaxed
   */
  private int playerWaxedOrUnwaxedCopperBlockID;
}
