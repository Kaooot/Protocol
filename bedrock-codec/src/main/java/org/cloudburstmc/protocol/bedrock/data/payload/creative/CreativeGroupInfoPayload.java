package org.cloudburstmc.protocol.bedrock.data.payload.creative;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

/**
 * @author Kaooot
 */
@Data
public class CreativeGroupInfoPayload {

    private CreativeItemCategory creativeCategory;
    private String name;
    private ItemData groupIconItem;
}