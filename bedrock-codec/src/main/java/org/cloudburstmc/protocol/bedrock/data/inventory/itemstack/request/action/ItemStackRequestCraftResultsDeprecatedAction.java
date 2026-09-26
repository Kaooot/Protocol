package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;

import java.util.List;

@Data
public class ItemStackRequestCraftResultsDeprecatedAction {

    private ItemStackRequestActionType actionType;
    private final List<ItemData> craftResults = new ObjectArrayList<>();
    private int numCrafts;
}