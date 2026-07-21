package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestCraftRecipeAction {
  private ItemStackRequestActionType actionType;

  private RecipeNetId recipeNetId;

  private int numberOfRequestedCrafts;
}
