package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CraftingCatalogGroup {
    private final CreativeItemCategory creativeCategory;
    private final String name;
    private final ItemData groupIconItem;
}
