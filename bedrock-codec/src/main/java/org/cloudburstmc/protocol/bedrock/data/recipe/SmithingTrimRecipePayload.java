package org.cloudburstmc.protocol.bedrock.data.recipe;

import lombok.Data;

@Data
public class SmithingTrimRecipePayload {

    private String recipeId;
    private RecipeIngredient templateIngredient;
    private RecipeIngredient baseIngredient;
    private RecipeIngredient additionIngredient;
    private String tag;
    private RecipeNetId netId;
}
