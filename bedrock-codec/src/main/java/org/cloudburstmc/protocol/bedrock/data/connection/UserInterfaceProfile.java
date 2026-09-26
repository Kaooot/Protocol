package org.cloudburstmc.protocol.bedrock.data.connection;

public enum UserInterfaceProfile {

    CLASSIC,
    POCKET,
    NONE;

    private static final UserInterfaceProfile[] VALUES = values();

    public static UserInterfaceProfile from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown UserInterfaceProfile ID: " + ordinal);
    }
}