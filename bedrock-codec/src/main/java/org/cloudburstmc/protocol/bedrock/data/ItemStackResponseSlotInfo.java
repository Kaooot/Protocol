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
  private int requestedSlot;

  private int slot;

  private int amount;

  private ItemStackNetId itemStackNetId;

  private RedactableString customName;

  private int durabilityCorrection;
}
