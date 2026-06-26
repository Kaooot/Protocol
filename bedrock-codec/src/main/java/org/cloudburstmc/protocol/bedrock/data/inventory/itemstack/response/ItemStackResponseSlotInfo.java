package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import lombok.*;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackNetId;

/**
 * ItemEntry holds information on what item stack should be present in a specific slot.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackResponseSlotInfo {
    private int requestedSlot;
    private int slot;
    private int amount;

    /**
     * itemStackNetId is the network ID of the new stack at a specific slot.
     */
    private ItemStackNetId itemStackNetId;

    /**
     * Holds the final custom name of a renamed item, if relevant.
     *
     * @since v422
     */
    private @NonNull RedactableString customName;

    /**
     * @since v428
     */
    private int durabilityCorrection;
}