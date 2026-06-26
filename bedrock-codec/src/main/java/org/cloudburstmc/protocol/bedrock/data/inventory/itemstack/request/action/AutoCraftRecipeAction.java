package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.RecipeNetId;

import java.util.List;

/**
 * AutoCraftRecipeStackRequestActionData is sent by the client similarly to the CraftRecipeStackRequestActionData. The
 * only difference is that the recipe is automatically created and crafted by shift clicking the recipe book.
 */
@Value
public class AutoCraftRecipeAction implements RecipeItemStackRequestAction {
    RecipeNetId recipeNetId;
    /**
     * @since v448
     */
    int timesCrafted;

    /**
     * @since v557
     */
    List<RecipeIngredient> ingredients;

    /**
     * @since v712
     */
    int numberOfRequestedCrafts;

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE_AUTO;
    }
}
