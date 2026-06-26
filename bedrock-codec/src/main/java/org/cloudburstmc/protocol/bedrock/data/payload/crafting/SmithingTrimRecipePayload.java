package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;

/**
 * @author Kaooot
 */
@Data
public class SmithingTrimRecipePayload {

    private String recipeId;
    private RecipeIngredient templateIngredient;
    private RecipeIngredient baseIngredient;
    private RecipeIngredient additionIngredient;
    private String tag;
    private RecipeNetId netId;
}