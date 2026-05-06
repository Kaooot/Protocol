package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

/**
 * @author Kaooot
 */
@Data
public class InventoryAction {

    private InventorySource source;
    private int slot;
    private ItemData fromItem;
    private ItemData toItem;
}