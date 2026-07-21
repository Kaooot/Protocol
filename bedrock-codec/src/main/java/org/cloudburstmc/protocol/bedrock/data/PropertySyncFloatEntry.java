package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class PropertySyncFloatEntry {
  private int propertyIndex;

  private float data;
}
