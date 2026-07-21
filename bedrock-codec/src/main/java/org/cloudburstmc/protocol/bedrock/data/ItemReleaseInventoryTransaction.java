package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class ItemReleaseInventoryTransaction {
  private InventoryTransaction actions;

  private ItemReleaseActionType actionType;

  private int slot;

  private NetworkItemStackDescriptor item;

  private Vector3f fromPosition;
}
