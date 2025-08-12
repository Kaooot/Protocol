package org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe;

import lombok.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

import java.util.List;
import java.util.UUID;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapelessRecipe implements GridCraftingDataEntry {

    private final CraftingDataEntryType type;
    private final String recipeUniqueId;
    private final List<ItemDescriptorWithCount> ingredientList;
    private final List<ItemData> productionList;
    private final UUID recipeID;
    private final String recipeTag;
    private final int priority;
    private final int netId;
    /**
     * @since v685
     */
    private final RecipeUnlockingRequirement unlockingRequirement;

    public static ShapelessRecipe of(CraftingDataEntryType type, String id, List<ItemDescriptorWithCount> ingredients,
                                     List<ItemData> results, UUID uuid, String tag, int priority, int netId,
                                     RecipeUnlockingRequirement requirement) {
        checkArgument(type == CraftingDataEntryType.SHAPELESS_RECIPE || type == CraftingDataEntryType.SHAPELESS_CHEMISTRY_RECIPE || type == CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE,
                "type must be SHAPELESS_RECIPE, SHAPELESS_CHEMISTRY_RECIPE or USER_DATA_SHAPELESS_RECIPE");
        return new ShapelessRecipe(type, id, ingredients, results, uuid, tag, priority, netId, requirement);
    }

    public static ShapelessRecipe of(CraftingDataEntryType type, String id, List<ItemDescriptorWithCount> ingredients,
                                     List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
        return ShapelessRecipe.of(type, id, ingredients, results, uuid, tag, priority, netId,
                RecipeUnlockingRequirement.INVALID);
    }

    public static ShapelessRecipe shapeless(String id, List<ItemDescriptorWithCount> ingredients,
                                            List<ItemData> results, UUID uuid, String tag, int priority, int netId,
                                            RecipeUnlockingRequirement requirement) {
        return of(CraftingDataEntryType.SHAPELESS_RECIPE, id, ingredients, results, uuid, tag, priority, netId, requirement);
    }

    public static ShapelessRecipe shapelessChemistry(String id, List<ItemDescriptorWithCount> ingredients,
                                                     List<ItemData> results, UUID uuid, String tag, int priority,
                                                     int netId) {
        return of(CraftingDataEntryType.SHAPELESS_CHEMISTRY_RECIPE, id, ingredients, results, uuid, tag, priority, netId);
    }

    public static ShapelessRecipe shulkerBox(String id, List<ItemDescriptorWithCount> ingredients,
                                             List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
        return of(CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE, id, ingredients, results, uuid, tag, priority, netId);
    }
}
