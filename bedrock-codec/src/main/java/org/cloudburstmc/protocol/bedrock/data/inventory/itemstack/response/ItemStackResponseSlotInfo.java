package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId;

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