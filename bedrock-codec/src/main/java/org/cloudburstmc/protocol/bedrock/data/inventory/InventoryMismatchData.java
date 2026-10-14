package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransaction;

@Data
public class InventoryMismatchData {

    private InventoryTransaction actions;
}