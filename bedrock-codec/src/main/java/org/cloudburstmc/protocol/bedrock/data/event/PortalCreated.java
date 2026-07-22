package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Provides portal creation telemetry data. For nether portals only
 */
@Data
public class PortalCreated {
  /**
   * The target dimension of the portal created
   */
  private int dimensionID;
}
