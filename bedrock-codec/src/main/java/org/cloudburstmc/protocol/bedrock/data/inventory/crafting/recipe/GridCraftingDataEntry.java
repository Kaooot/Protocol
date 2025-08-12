package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

import java.util.List;

/**
 * A recipe that can be used on a grid-like table.
 */
public interface GridCraftingDataEntry extends TaggedCraftingDataEntry, UniqueCraftingDataEntry, IdentifiableRecipeData {

    List<ItemDescriptorWithCount> getIngredientList();

    List<ItemData> getProductionList();

    int getPriority();

    RecipeUnlockingRequirement getUnlockingRequirement();
}
