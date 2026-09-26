package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;

@Data
public class ItemStackRequestCreateAction {

    private ItemStackRequestActionType actionType;
    private int resultsIndex;
}