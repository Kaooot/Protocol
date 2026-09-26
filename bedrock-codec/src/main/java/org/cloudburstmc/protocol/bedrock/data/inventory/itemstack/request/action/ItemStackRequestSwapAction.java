package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;

@Data
public class ItemStackRequestSwapAction {

    private ItemStackRequestActionType actionType;
    private ItemStackRequestSlotInfo source;
    private ItemStackRequestSlotInfo destination;
}