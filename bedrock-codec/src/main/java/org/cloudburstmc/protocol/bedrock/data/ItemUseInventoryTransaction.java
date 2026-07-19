package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUseInventoryTransaction {
  private InventoryTransaction Actions;

  private ItemUseActionType ActionType;

  private ItemUseTriggerType TriggerType;

  private Vector3i Position;

  private int Face;

  private int Slot;

  private NetworkItemStackDescriptor Item;

  private Vector3f FromPosition;

  private Vector3f ClickPosition;

  private int TargetBlockId;

  private ItemUsePredictedResult ClientInteractPrediction;

  private ItemUseClientCooldownState ClientCooldownState;
}
