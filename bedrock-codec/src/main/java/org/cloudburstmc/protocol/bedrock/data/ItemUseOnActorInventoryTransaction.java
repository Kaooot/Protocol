package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUseOnActorInventoryTransaction {
  private InventoryTransaction actions;

  private long runtimeId;

  private ItemUseOnActorActionType actionType;

  private int slot;

  private NetworkItemStackDescriptor item;

  private Vector3f fromPosition;

  private Vector3f hitPosition;
}
