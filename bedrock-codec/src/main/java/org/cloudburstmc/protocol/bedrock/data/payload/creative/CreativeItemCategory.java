package org.cloudburstmc.protocol.bedrock.data.payload.creative;

/**
 * @author Kaooot
 */
public enum CreativeItemCategory {

    ALL,
    CONSTRUCTION,
    NATURE,
    EQUIPMENT,
    ITEMS,
    ITEM_COMMAND_ONLY,
    UNDEFINED;

    private static final CreativeItemCategory[] VALUES = values();

    public static CreativeItemCategory from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown CreativeItemCategory ID: " + ordinal);
    }
}