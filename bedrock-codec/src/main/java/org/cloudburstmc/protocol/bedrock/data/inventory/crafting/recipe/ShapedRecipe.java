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
public class ShapedRecipe implements GridCraftingDataEntry {

    private final CraftingDataEntryType type;
    private final String recipeUniqueId;
    private final int recipeWidth;
    private final int recipeHeight;
    private final List<ItemDescriptorWithCount> ingredientGrid;
    private final List<ItemData> productionList;
    private final UUID recipeID;
    private final String recipeTag;
    private final int priority;
    private final int netId;
    /**
     * @since v671
     */
    private final boolean assumeSymmetry;
    /**
     * @since v685
     */
    private final RecipeUnlockingRequirement unlockingRequirement;

    public static ShapedRecipe of(CraftingDataEntryType type, String id, int width, int height,
                                  List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid,
                                  String tag, int priority, int netId) {
        return ShapedRecipe.of(type, id, width, height, ingredients, results, uuid, tag, priority, netId, false,
                RecipeUnlockingRequirement.INVALID);
    }

    public static ShapedRecipe of(CraftingDataEntryType type, String id, int width, int height,
                                  List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid,
                                  String tag, int priority, int netId, boolean assumeSymetry, RecipeUnlockingRequirement requirement) {
        checkArgument(type == CraftingDataEntryType.SHAPED_RECIPE || type == CraftingDataEntryType.SHAPED_CHEMISTRY_RECIPE,
                "type must be SHAPED_RECIPE or SHAPED_CHEMISTRY_RECIPE");
        return new ShapedRecipe(type, id, width, height, ingredients, results, uuid, tag, priority, netId, assumeSymetry, requirement);
    }

    public static ShapedRecipe of(CraftingDataEntryType type, String id, int width, int height,
                                  List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid,
                                  String tag, int priority, int netId, boolean assumeSymetry) {
        return ShapedRecipe.of(type, id, width, height, ingredients, results, uuid, tag, priority, netId, assumeSymetry,
                RecipeUnlockingRequirement.INVALID);
    }

    public static ShapedRecipe shaped(String id, int width, int height, List<ItemDescriptorWithCount> ingredients,
                                      List<ItemData> results, UUID uuid, String tag, int priority, int netId, boolean assumeSymetry,
                                      RecipeUnlockingRequirement requirement) {
        return of(CraftingDataEntryType.SHAPED_RECIPE, id, width, height, ingredients, results, uuid, tag, priority, netId, assumeSymetry, requirement);
    }

    public static ShapedRecipe shapedChemistry(String id, int width, int height,
                                               List<ItemDescriptorWithCount> ingredients, List<ItemData> results,
                                               UUID uuid, String tag, int priority, int netId, boolean assumeSymetry) {
        return of(CraftingDataEntryType.SHAPED_CHEMISTRY_RECIPE, id, width, height, ingredients, results, uuid, tag, priority, netId,
                assumeSymetry);
    }

    @Override
    public List<ItemDescriptorWithCount> getIngredientList() {
        return this.ingredientGrid;
    }
}
