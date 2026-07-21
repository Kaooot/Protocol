package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemReleaseInventoryTransaction {
  private InventoryTransaction actions;

  private ItemReleaseActionType actionType;

  private int slot;

  private NetworkItemStackDescriptor item;

  private Vector3f fromPosition;
}
