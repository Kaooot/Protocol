package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestNetworkItemInstanceDescriptor;

import java.util.List;

/**
 * CraftResultsDeprecatedStackRequestAction is an additional, deprecated packet sent by the client after
 * crafting. It holds the final results and the amount of times the recipe was crafted. It shouldn't be used.
 * This action is also sent when an item is enchanted. Enchanting should be treated mostly the same way as
 * crafting, where the old item is consumed.
 */
@Value
public class CraftResultsDeprecatedAction implements ItemStackRequestAction {
    /**
     * @deprecated since v2168
     */
    ItemData[] resultItemsDeprecated;
    /**
     * @since v2168
     */
    List<ItemStackRequestNetworkItemInstanceDescriptor> craftResults;
    int numCrafts;

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RESULTS;
    }
}
