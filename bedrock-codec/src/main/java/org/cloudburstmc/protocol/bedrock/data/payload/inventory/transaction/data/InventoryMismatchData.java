package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.InventoryTransaction;

/**
 * @author Kaooot
 */
@Data
public class InventoryMismatchData implements InventoryTransactionData {

    private InventoryTransaction actions;

    @Override
    public InventoryTransactionDataType getType() {
        return InventoryTransactionDataType.MISMATCH;
    }
}