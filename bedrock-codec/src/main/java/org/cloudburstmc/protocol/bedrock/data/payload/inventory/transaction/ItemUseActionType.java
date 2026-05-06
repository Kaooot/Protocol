package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction;

/**
 * @author Kaooot
 */
public enum ItemUseActionType {

    PLACE,
    USE,
    DESTROY,
    USE_AS_ATTACK;

    private static final ItemUseActionType[] VALUES = values();

    public static ItemUseActionType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ItemUseActionType ID: " + ordinal);
    }
}