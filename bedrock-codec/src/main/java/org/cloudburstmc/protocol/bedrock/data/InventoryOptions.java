package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class InventoryOptions {
  private InventoryLeftTabIndex leftInventoryTab;

  private InventoryRightTabIndex rightInventoryTab;

  private boolean filtering;

  private InventoryLayout layoutInv;

  private InventoryLayout layoutCraft;
}
