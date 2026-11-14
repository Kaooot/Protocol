package org.cloudburstmc.protocol.bedrock.data.inventory;

public enum VirtualContainerCloseCause {

    UNKNOWN,
    USER_CLOSED,
    SERVER_REQUESTED,
    USER_BUSY,
    CLIENT_NOT_READY,
    INVALID_CONTENTS,
    UI_CREATION_FAILED,
    UI_CANCELLED;

    private static final VirtualContainerCloseCause[] VALUES = values();

    public static VirtualContainerCloseCause from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown VirtualContainerCloseCause ID: " + ordinal);
    }
}