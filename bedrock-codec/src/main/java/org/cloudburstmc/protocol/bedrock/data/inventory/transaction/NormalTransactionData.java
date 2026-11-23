package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import lombok.Data;

@Data
public class NormalTransactionData implements InventoryTransactionData {

    @Override
    public InventoryTransactionType getType() {
        return InventoryTransactionType.NORMAL;
    }


    private InventoryTransaction actions;
}