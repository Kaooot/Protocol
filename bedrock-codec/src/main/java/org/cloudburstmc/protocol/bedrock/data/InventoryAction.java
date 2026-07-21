package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryAction {
  private InventorySource source;

  private int slot;

  private NetworkItemStackDescriptor fromItem;

  private NetworkItemStackDescriptor toItem;
}
