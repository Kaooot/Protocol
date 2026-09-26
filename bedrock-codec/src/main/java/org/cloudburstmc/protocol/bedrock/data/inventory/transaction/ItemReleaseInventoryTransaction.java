package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.player.HandSlot;

@Data
public class ItemReleaseInventoryTransaction implements InventoryTransactionData {

    private InventoryTransaction actions;
    private ItemReleaseActionType actionType;
    private int slot;
    private ItemData item;
    private Vector3f fromPosition;
    /**
     * @since v2207
     */
    private HandSlot hand;

    @Override
    public InventoryTransactionDataType getType() {
        return InventoryTransactionDataType.ITEM_RELEASE;
    }
}
