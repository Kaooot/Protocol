package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

import lombok.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SmithingTransformRecipe implements TaggedCraftingDataEntry, IdentifiableRecipeData, NetworkCraftingDataEntry {

    private final String recipeUniqueId;
    private final ItemDescriptorWithCount templateIngredient;
    private final ItemDescriptorWithCount baseIngredient;
    private final ItemDescriptorWithCount additionIngredient;
    private final ItemData result;
    private final String recipeTag;
    private final int netId;

    @Override
    public CraftingDataEntryType getType() {
        return CraftingDataEntryType.SMITHING_TRANSFORM_RECIPE;
    }

    public static SmithingTransformRecipe of(String id, ItemDescriptorWithCount template, ItemDescriptorWithCount base,
                                             ItemDescriptorWithCount addition, ItemData result, String tag, int netId) {
        return new SmithingTransformRecipe(id, template, base, addition, result, tag, netId);
    }

    /**
     * Pre-1.19.80
     */
    public static SmithingTransformRecipe of(String id, ItemDescriptorWithCount base,
                                             ItemDescriptorWithCount addition, ItemData result, String tag, int netId) {
        return new SmithingTransformRecipe(id, null, base, addition, result, tag, netId);
    }
}
