package org.cloudburstmc.protocol.bedrock.data.payload.structure;

/**
 * @author Kaooot
 */
public enum Rotation {

    NONE,
    ROTATE_90,
    ROTATE_180,
    ROTATE_270,
    CLOCKWISE_90,
    CLOCKWISE_180,
    COUNTER_CLOCKWISE_90;

    private static final Rotation[] VALUES = values();

    public static Rotation from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown Rotation ID: " + ordinal);
    }
}