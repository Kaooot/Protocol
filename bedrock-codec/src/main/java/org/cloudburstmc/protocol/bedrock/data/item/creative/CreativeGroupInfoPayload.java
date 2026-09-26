package org.cloudburstmc.protocol.bedrock.data.item.creative;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

@Data
public class CreativeGroupInfoPayload {

    private CreativeCategory creativeCategory;
    private String name;
    private ItemData groupIconItem;
}