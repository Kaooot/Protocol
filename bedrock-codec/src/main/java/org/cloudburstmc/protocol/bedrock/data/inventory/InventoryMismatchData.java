package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionType;

@Data
public class InventoryMismatchData implements InventoryTransactionData {

    private InventoryTransaction actions;

    @Override
    public InventoryTransactionType getType() {
        return InventoryTransactionType.MISMATCH;
    }
}