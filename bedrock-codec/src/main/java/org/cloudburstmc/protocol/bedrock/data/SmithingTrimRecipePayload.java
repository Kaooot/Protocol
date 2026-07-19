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
public class SmithingTrimRecipePayload {
  private String RecipeId;

  private RecipeIngredient TemplateIngredient;

  private RecipeIngredient BaseIngredient;

  private RecipeIngredient AdditionIngredient;

  private String Tag;

  private RecipeNetId NetId;
}
