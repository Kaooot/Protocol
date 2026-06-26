package org.cloudburstmc.protocol.bedrock.data.payload.crafting;

/**
 * @author Kaooot
 */
public enum RecipeUnlockingContext {

    NONE,
    ALWAYS_UNLOCKED,
    PLAYER_IN_WATER,
    PLAYER_HAS_MANY_ITEMS;

    private static final RecipeUnlockingContext[] VALUES = values();

    public static RecipeUnlockingContext from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length ) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown RecipeUnlockingContext ID: " + ordinal);
    }
}