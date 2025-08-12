package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

import java.util.UUID;

public interface UniqueCraftingDataEntry extends NetworkCraftingDataEntry {

    UUID getRecipeID();
}
