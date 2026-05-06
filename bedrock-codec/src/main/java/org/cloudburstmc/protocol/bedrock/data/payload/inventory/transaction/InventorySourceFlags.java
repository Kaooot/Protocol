package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction;

/**
 * @author Kaooot
 */
public enum InventorySourceFlags {

    NO_FLAG,
    WORLD_INTERACTION_RANDOM,
    NONE;

    private static final InventorySourceFlags[] VALUES = values();

    public static InventorySourceFlags from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown InventorySourceFlags ID: " + ordinal);
    }
}