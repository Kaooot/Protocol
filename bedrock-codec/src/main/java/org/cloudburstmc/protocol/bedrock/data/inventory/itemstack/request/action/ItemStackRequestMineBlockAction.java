package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;

@Data
public class ItemStackRequestMineBlockAction {

    private ItemStackRequestActionType actionType;
    private int slot;
    private int predictedDurability;
    private int netIdVariant;
}