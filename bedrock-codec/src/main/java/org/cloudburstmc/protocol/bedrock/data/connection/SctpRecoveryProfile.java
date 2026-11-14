package org.cloudburstmc.protocol.bedrock.data.connection;

public enum SctpRecoveryProfile {

    DEFAULT,
    BALANCED,
    FAST;

    private static final SctpRecoveryProfile[] VALUES = values();

    public static SctpRecoveryProfile from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown SctpRecoveryProfile ID: " + ordinal);
    }
}