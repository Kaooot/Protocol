package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmithingTrimRecipePayload {
  private String recipeId;

  private RecipeIngredient templateIngredient;

  private RecipeIngredient baseIngredient;

  private RecipeIngredient additionIngredient;

  private String tag;

  private RecipeNetId netId;
}
