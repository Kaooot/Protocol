package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ItemStackRequestCraftRecipeOptionalAction {
  private ItemStackRequestActionType actionType;

  private RecipeNetId recipeNetId;

  private int filteredStringIndex;
}
