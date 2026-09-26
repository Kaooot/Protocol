package org.cloudburstmc.protocol.bedrock.data.item.creative;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

@Data
public class CreativeItemEntryPayload {

    private CreativeItemNetId creativeNetId;
    private ItemData itemInstance;
    private int groupIndex;
}