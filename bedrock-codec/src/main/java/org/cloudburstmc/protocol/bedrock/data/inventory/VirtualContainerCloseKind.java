package org.cloudburstmc.protocol.bedrock.data.inventory;

public enum VirtualContainerCloseKind {

    REQUEST,
    CLOSED,
    CONFIRMED;

    private static final VirtualContainerCloseKind[] VALUES = values();

    public static VirtualContainerCloseKind from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown VirtualContainerCloseKind ID: " + ordinal);
    }
}