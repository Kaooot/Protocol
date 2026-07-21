package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class PortalUsed {
  private int sourceDimensionID;

  private int targetDimensionID;
}
