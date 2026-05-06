package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemUseOnActorActionType;

/**
 * @author Kaooot
 */
@Data
public class ItemUseOnActorInventoryTransaction implements InventoryTransactionData {

    private InventoryTransaction actions;
    private long runtimeId;
    private ItemUseOnActorActionType actionType;
    private int slot;
    private ItemData item;
    private Vector3f fromPosition;
    private Vector3f hitPosition;

    @Override
    public InventoryTransactionDataType getType() {
        return InventoryTransactionDataType.ITEM_USE_ON_ACTOR;
    }
}