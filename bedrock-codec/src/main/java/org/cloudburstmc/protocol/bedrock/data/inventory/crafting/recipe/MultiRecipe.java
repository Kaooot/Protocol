package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

import lombok.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiRecipe implements UniqueCraftingDataEntry {

    private final UUID recipeID;
    private final int netId;

    @Override
    public CraftingDataEntryType getType() {
        return CraftingDataEntryType.MULTI_RECIPE;
    }

    public static MultiRecipe of(UUID uuid, int netId) {
        return new MultiRecipe(uuid, netId);
    }
}
