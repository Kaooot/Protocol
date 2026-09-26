package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import lombok.Data;

@Data
public class NormalTransactionData implements InventoryTransactionData {

    private InventoryTransaction actions;

    @Override
    public InventoryTransactionDataType getType() {
        return InventoryTransactionDataType.NORMAL;
    }
}
