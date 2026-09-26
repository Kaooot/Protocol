package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;

@Data
public class ItemStackRequestLabTableCombineAction {

    private ItemStackRequestActionType actionType;
}