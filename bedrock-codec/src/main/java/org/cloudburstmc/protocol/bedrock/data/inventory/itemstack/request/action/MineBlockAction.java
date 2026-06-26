package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Value;

/**
 * MineBlockStackRequestActionData is sent by the client when it breaks a block.
 */
@Value
public class MineBlockAction implements ItemStackRequestAction {
    int slot;
    int predictedDurability;
    int stackNetworkId;

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK;
    }

}
