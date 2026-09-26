package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;

@Data
public class ItemStackRequestCraftRepairAndDisenchantAction {

    private ItemStackRequestActionType actionType;
    private RecipeNetId recipeNetId;
    /**
     * @since v712
     */
    private int numberOfRequestedCrafts;
    private int repairCost;
}