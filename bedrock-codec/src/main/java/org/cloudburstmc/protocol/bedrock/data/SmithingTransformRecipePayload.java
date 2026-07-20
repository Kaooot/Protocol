package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmithingTransformRecipePayload {
  private String recipeId;

  private RecipeIngredient templateIngredient;

  private RecipeIngredient baseIngredient;

  private RecipeIngredient additionIngredient;

  private NetworkItemInstanceDescriptorData result;

  private String tag;

  private RecipeNetId netId;
}
