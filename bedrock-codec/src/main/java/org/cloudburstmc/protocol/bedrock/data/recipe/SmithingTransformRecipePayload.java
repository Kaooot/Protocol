package org.cloudburstmc.protocol.bedrock.data.recipe;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

@Data
public class SmithingTransformRecipePayload {

    private String recipeId;
    private RecipeIngredient templateIngredient;
    private RecipeIngredient baseIngredient;
    private RecipeIngredient additionIngredient;
    private ItemData result;
    private String tag;
    private RecipeNetId netId;
}
