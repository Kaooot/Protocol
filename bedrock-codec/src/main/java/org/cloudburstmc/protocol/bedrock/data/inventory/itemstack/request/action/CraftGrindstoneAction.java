package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.RecipeNetId;

@Value
public class CraftGrindstoneAction implements ItemStackRequestAction {
    RecipeNetId recipeNetId;
    /**
     * @since v712
     */
    int numberOfRequestedCrafts;
    int repairCost;

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT;
    }
}