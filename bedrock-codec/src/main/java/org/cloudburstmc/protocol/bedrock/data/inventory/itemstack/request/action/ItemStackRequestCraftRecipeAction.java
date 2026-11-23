package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;

@Data
public class ItemStackRequestCraftRecipeAction {

    private ItemStackRequestActionType actionType;
    private RecipeNetId recipeNetId;
    @Added("1.21.20")
    private int numberOfRequestedCrafts;
}