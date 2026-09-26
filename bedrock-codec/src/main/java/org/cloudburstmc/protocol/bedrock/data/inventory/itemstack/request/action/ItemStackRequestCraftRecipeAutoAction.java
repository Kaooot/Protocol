package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;

import java.util.List;

@Data
public class ItemStackRequestCraftRecipeAutoAction {

    private ItemStackRequestActionType actionType;
    private RecipeNetId recipeNetId;
    /**
     * @since v712
     */
    private int numberOfRequestedCrafts;
    /**
     * @since v448
     * @deprecated since v2168
     */
    private int timesCrafted;
    /**
     * @since v557
     */
    private final List<RecipeIngredient> ingredients = new ObjectArrayList<>();
}