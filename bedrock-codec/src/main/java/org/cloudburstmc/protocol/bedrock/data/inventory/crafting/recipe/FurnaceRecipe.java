package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

import lombok.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

/**
 * @deprecated since v975. Use {@link ShapelessRecipe}.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Deprecated
public class FurnaceRecipe implements TaggedCraftingDataEntry {

    private final CraftingDataEntryType type;
    private final int inputId;
    private final int inputData;
    private final ItemData resultItem;
    private final String recipeTag;

    public boolean hasData() {
        return type == CraftingDataEntryType.FURNACE_AUX_RECIPE;
    }

    public static FurnaceRecipe of(CraftingDataEntryType type, int inputId, int inputData, ItemData result, String tag) {
        checkArgument(type == CraftingDataEntryType.FURNACE_RECIPE || type == CraftingDataEntryType.FURNACE_AUX_RECIPE,
                "type must be FURNACE_RECIPE or FURNACE_AUX_RECIPE");
        return new FurnaceRecipe(type, inputId, inputData, result, tag);
    }

    public static FurnaceRecipe of(int inputId, ItemData result, String tag) {
        return new FurnaceRecipe(CraftingDataEntryType.FURNACE_RECIPE, inputId, -1, result, tag);
    }

    public static FurnaceRecipe of(int inputId, int inputData, ItemData result, String tag) {
        return new FurnaceRecipe(CraftingDataEntryType.FURNACE_AUX_RECIPE, inputId, inputData, result, tag);
    }
}
