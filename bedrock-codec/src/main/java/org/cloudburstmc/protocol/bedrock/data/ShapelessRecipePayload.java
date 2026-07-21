package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ShapelessRecipePayload {
  private String recipeId;

  private final List<RecipeIngredient> ingredients = new ObjectArrayList<>();

  private final List<NetworkItemInstanceDescriptorData> results = new ObjectArrayList<>();

  private UUID uuid;

  private String tag;

  private int priority;

  private RecipeUnlockingRequirement unlockingRequirement;

  private RecipeNetId netId;
}
