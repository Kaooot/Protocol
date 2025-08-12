package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

import lombok.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SmithingTrimRecipe implements TaggedCraftingDataEntry, IdentifiableRecipeData, NetworkCraftingDataEntry {

    private final String recipeUniqueId;
    private final ItemDescriptorWithCount templateIngredient;
    private final ItemDescriptorWithCount baseIngredient;
    private final ItemDescriptorWithCount additionIngredient;
    private final String recipeTag;
    private final int netId;

    @Override
    public CraftingDataEntryType getType() {
        return CraftingDataEntryType.SMITHING_TRIM_RECIPE;
    }

    public static SmithingTrimRecipe of(String id, ItemDescriptorWithCount base, ItemDescriptorWithCount addition,
                                        ItemDescriptorWithCount template, String tag, int netId) {
        return new SmithingTrimRecipe(id, base, addition, template, tag, netId);
    }
}
