package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotInfo;

@Data
public class ItemStackRequestDropAction {

    private ItemStackRequestActionType actionType;
    private int amount;
    private ItemStackRequestSlotInfo source;
    private boolean randomly;
}