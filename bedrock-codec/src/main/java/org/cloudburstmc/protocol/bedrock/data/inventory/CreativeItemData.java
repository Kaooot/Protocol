package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CreativeItemData {
    private final ItemData itemInstance;
    private final int creativeNetId;
    private final int groupIndex;
}
