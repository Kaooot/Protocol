package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackResponseSlotInfo {
  private int RequestedSlot;

  private int Slot;

  private int Amount;

  private ItemStackNetId ItemStackNetId;

  private BedrockSafetyRedactableString CustomName;

  private int DurabilityCorrection;
}
