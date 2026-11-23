package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;

import java.util.List;

@Data
public class ItemStackRequestCraftRecipeAutoAction {

    private ItemStackRequestActionType actionType;
    private RecipeNetId recipeNetId;
    @Added("1.21.20")
    private int numberOfRequestedCrafts;
    @Deprecated
    @Added("1.17.0")
    @Removed("26.40")
    private int timesCrafted;
    @Added("1.19.40")
    private final List<RecipeIngredient> ingredients = new ObjectArrayList<>();
}