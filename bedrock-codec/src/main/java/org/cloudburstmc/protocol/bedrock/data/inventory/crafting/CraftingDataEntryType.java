package org.cloudburstmc.protocol.bedrock.data.inventory.crafting;

public enum CraftingDataEntryType {
    SHAPELESS_RECIPE,
    SHAPED_RECIPE,
    FURNACE_RECIPE,
    FURNACE_AUX_RECIPE,
    MULTI_RECIPE,
    USER_DATA_SHAPELESS_RECIPE,
    SHAPELESS_CHEMISTRY_RECIPE,
    SHAPED_CHEMISTRY_RECIPE,
    /**
     * @since v567
     */
    SMITHING_TRANSFORM_RECIPE,
    /**
     * @since v582
     */
    SMITHING_TRIM_RECIPE;

    private static final CraftingDataEntryType[] VALUES = values();

    public static CraftingDataEntryType byId(int id) {
        if (id >= 0 && id < VALUES.length) {
            return VALUES[id];
        }
        throw new UnsupportedOperationException("Unknown CraftingDataEntryType ID: " + id);
    }
}