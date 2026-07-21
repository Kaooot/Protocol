package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryOptions {
  private InventoryLeftTabIndex leftInventoryTab;

  private InventoryRightTabIndex rightInventoryTab;

  private boolean filtering;

  private InventoryLayout layoutInv;

  private InventoryLayout layoutCraft;
}
