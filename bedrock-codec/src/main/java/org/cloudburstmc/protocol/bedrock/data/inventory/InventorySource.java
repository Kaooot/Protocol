package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Data;

@Data
public class InventorySource {

    private InventorySourceType sourceType;
    private Integer containerID;
    private InventorySourceFlags bitFlags;
}