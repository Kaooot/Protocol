package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;

import java.util.List;

@Value
public class RecipeUnlockingRequirement {
    public static final RecipeUnlockingRequirement INVALID = new RecipeUnlockingRequirement(RecipeUnlockingContext.NONE);

    RecipeUnlockingContext unlockingContext;
    List<RecipeIngredient> unlockingIngredients = new ObjectArrayList<>();

    public boolean isInvalid() {
        return this.unlockingIngredients.isEmpty() && this.unlockingContext.equals(RecipeUnlockingContext.NONE);
    }
}