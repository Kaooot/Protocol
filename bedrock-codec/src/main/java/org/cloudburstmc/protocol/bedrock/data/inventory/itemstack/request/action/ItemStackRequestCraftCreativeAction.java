package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeItemNetId;

@Data
public class ItemStackRequestCraftCreativeAction implements ItemStackRequestAction {

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_CREATIVE;
    }


    private ItemStackRequestActionType actionType;
    private CreativeItemNetId creativeItemNetId;
    @Added("1.21.20")
    private int numberOfRequestedCrafts;
}