package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemReleaseActionType;

/**
 * @author Kaooot
 */
@Data
public class ItemReleaseInventoryTransaction implements InventoryTransactionData {

    private InventoryTransaction actions;
    private ItemReleaseActionType actionType;
    private int slot;
    private ItemData item;
    private Vector3f fromPosition;

    @Override
    public InventoryTransactionDataType getType() {
        return InventoryTransactionDataType.ITEM_RELEASE;
    }
}