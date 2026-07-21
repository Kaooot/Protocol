package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class ItemUseOnActorInventoryTransaction {
  private InventoryTransaction actions;

  private long runtimeId;

  private ItemUseOnActorActionType actionType;

  private int slot;

  private NetworkItemStackDescriptor item;

  private Vector3f fromPosition;

  private Vector3f hitPosition;
}
