package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUseInventoryTransaction {
  private InventoryTransaction actions;

  private ItemUseActionType actionType;

  private ItemUseTriggerType triggerType;

  private Vector3i position;

  private int face;

  private int slot;

  private NetworkItemStackDescriptor item;

  private Vector3f fromPosition;

  private Vector3f clickPosition;

  private int targetBlockId;

  private ItemUsePredictedResult clientInteractPrediction;

  private ItemUseClientCooldownState clientCooldownState;
}
