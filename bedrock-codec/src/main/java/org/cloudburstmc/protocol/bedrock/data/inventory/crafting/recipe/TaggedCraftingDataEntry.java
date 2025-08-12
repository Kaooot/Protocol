package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

public interface TaggedCraftingDataEntry extends CraftingDataEntry {

    String getRecipeTag();
}
