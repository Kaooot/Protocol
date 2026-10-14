package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

import lombok.Data;

@Data
public class NormalTransactionData {

    private InventoryTransaction actions;
}