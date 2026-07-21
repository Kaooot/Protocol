package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class InventoryAction {
  private InventorySource source;

  private int slot;

  private NetworkItemStackDescriptor fromItem;

  private NetworkItemStackDescriptor toItem;
}
