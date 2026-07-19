package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShapedRecipePayload {
  private String RecipeId;

  private int Width;

  private int Height;

  private final List<RecipeIngredient> Ingredients = new ObjectArrayList<>();

  private final List<NetworkItemInstanceDescriptorData> Results = new ObjectArrayList<>();

  private UUID UUID;

  private String Tag;

  private int Priority;

  private boolean AssumeSymmetry;

  private RecipeUnlockingRequirement UnlockingRequirement;

  private RecipeNetId NetId;
}
