package org.cloudburstmc.protocol.bedrock.data.payload.move;

public enum TeleportationCause {

    UNKNOWN,
    PROJECTILE,
    CHORUS_FRUIT,
    COMMAND,
    BEHAVIOR;

    private static final TeleportationCause[] VALUES = values();

    public static TeleportationCause from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown TeleportationCause ID: " + ordinal);
    }
}