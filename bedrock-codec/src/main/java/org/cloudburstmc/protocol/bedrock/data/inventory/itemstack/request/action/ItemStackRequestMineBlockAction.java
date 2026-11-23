package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;

@Data
public class ItemStackRequestMineBlockAction implements ItemStackRequestAction {

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK;
    }


    private ItemStackRequestActionType actionType;
    private int slot;
    private int predictedDurability;
    private int netIdVariant;
}