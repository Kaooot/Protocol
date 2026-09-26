package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;

@Data
public class ItemStackRequestCraftRecipeOptionalAction {

    private ItemStackRequestActionType actionType;
    private RecipeNetId recipeNetId;
    private int filteredStringIndex;
}