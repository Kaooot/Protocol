package org.cloudburstmc.protocol.bedrock.data.inventory;

public enum InventoryLayout {

    NONE,
    INVENTORY_ONLY,
    DEFAULT,
    RECIPE_BOOK_ONLY;

    private static final InventoryLayout[] VALUES = values();

    public static InventoryLayout from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown InventoryLayout ID: " + ordinal);
    }
}