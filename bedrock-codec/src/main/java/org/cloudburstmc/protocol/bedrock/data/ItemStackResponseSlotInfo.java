package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackResponseSlotInfo {
  private int requestedSlot;

  private int slot;

  private int amount;

  private ItemStackNetId itemStackNetId;

  private RedactableString customName;

  private int durabilityCorrection;
}
