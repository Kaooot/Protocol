package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data;

import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;

/**
 * @author Kaooot
 */
public interface InventoryTransactionData {

    InventoryTransactionDataType getType();

    InventoryTransaction getActions();

    void setActions(InventoryTransaction actions);
}