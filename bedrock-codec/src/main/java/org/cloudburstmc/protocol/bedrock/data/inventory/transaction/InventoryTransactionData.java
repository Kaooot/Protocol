package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

public interface InventoryTransactionData {

    InventoryTransactionDataType getType();

    InventoryTransaction getActions();

    void setActions(InventoryTransaction actions);
}
