package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class SmithingTransformRecipePayload {
  private String recipeId;

  private RecipeIngredient templateIngredient;

  private RecipeIngredient baseIngredient;

  private RecipeIngredient additionIngredient;

  private NetworkItemInstanceDescriptorData result;

  private String tag;

  private RecipeNetId netId;
}
