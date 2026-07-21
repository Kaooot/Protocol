package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ItemStackRequestCraftRecipeAutoAction {
  private ItemStackRequestActionType actionType;

  private RecipeNetId recipeNetId;

  private int numberOfRequestedCrafts;

  private final List<RecipeIngredient> ingredients = new ObjectArrayList<>();
}
