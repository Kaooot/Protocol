package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

@Data
public class InventoryAction {

    private InventorySource source;
    private int slot;
    private ItemData fromItem;
    private ItemData toItem;
}