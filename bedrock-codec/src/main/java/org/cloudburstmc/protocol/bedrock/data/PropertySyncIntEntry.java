package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class PropertySyncIntEntry {
  private int propertyIndex;

  private int data;
}
