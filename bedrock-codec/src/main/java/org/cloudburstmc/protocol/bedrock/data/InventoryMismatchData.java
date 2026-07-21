package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class InventoryMismatchData {
  private InventoryTransaction actions;
}
