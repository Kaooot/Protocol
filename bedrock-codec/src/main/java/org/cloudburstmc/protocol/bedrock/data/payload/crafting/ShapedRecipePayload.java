package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;

import java.util.List;
import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class ShapedRecipePayload {

    private String recipeId;
    private int width;
    private int height;
    private final List<RecipeIngredient> ingredients = new ObjectArrayList<>();
    private final List<ItemData> results = new ObjectArrayList<>();
    private UUID uuid;
    private String tag;
    private int priority;
    private boolean assumeSymmetry;
    private RecipeUnlockingRequirement unlockingRequirement;
    private RecipeNetId netId;
}