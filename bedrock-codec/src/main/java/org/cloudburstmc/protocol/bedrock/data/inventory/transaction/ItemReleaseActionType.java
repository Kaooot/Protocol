package org.cloudburstmc.protocol.bedrock.data.inventory.transaction;

public enum ItemReleaseActionType {

    RELEASE,
    USE;

    private static final ItemReleaseActionType[] VALUES = values();

    public static ItemReleaseActionType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ItemReleaseActionType ID: " + ordinal);
    }
}