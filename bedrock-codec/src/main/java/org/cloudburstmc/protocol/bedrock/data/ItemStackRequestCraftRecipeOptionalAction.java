package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequestCraftRecipeOptionalAction {
  private ItemStackRequestActionType actionType;

  private RecipeNetId recipeNetId;

  private int filteredStringIndex;
}
