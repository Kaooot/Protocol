package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionDataType;

@Data
public class InventoryMismatchData implements InventoryTransactionData {

    private InventoryTransaction actions;

    @Override
    public InventoryTransactionDataType getType() {
        return InventoryTransactionDataType.MISMATCH;
    }
}
