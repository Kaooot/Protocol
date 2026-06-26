package org.cloudburstmc.protocol.bedrock.data.payload.creative;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

/**
 * @author Kaooot
 */
@Data
public class CreativeItemEntryPayload {

    private CreativeItemNetId creativeNetId;
    private ItemData itemInstance;
    private int groupIndex;
}