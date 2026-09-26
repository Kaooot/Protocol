package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeItemNetId;

@Data
public class ItemStackRequestCraftCreativeAction {

    private ItemStackRequestActionType actionType;
    private CreativeItemNetId creativeItemNetId;
    /**
     * @since v712
     */
    private int numberOfRequestedCrafts;
}