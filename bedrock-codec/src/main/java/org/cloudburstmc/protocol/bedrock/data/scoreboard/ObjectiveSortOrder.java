package org.cloudburstmc.protocol.bedrock.data.scoreboard;

public enum ObjectiveSortOrder {

    ASCENDING,
    DESCENDING;

    private static final ObjectiveSortOrder[] VALUES = values();

    public static ObjectiveSortOrder from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ObjectiveSortOrder ID: " + ordinal);
    }
}