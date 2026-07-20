package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestCraftRecipeAction {
  private ItemStackRequestActionType actionType;

  private RecipeNetId recipeNetId;

  private int numberOfRequestedCrafts;
}
