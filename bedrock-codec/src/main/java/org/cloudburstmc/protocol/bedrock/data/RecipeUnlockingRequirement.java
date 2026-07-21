package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RecipeUnlockingRequirement {
  private RecipeUnlockingContext unlockingContext;

  private final List<RecipeIngredient> unlockingIngredients = new ObjectArrayList<>();
}
