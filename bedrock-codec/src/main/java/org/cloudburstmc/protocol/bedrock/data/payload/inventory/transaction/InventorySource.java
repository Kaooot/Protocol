package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class InventorySource {

    private InventorySourceType sourceType;
    private int containerID;
    private InventorySourceFlags bitFlags;
}