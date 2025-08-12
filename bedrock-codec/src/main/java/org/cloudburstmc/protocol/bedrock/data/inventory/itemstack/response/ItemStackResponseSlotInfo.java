package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response;

import lombok.*;

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
    private int itemStackNetId;

    /**
     * Holds the final custom name of a renamed item, if relevant.
     *
     * @since v422
     */
    private @NonNull String customName;

    /**
     * @since v428
     */
    private int durabilityCorrection;
    /**
     * @since v766
     */
    private String filteredCustomName = "";
}