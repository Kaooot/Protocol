package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Provides portal use telemetry data
 */
@Data
public class PortalUsed {
  /**
   * The dimension the client is currently in
   */
  private int sourceDimensionID;

  /**
   * The target dimension the portal will lead to
   */
  private int targetDimensionID;
}
