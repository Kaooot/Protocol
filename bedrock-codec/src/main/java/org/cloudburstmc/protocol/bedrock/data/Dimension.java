package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum Dimension {

    OVERWORLD,
    NETHER,
    THE_END,
    UNDEFINED,
    CUSTOM;

    private static final Dimension[] VALUES = values();

    public static Dimension from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        if (ordinal < 1000) {
            throw new UnsupportedOperationException("Detected unknown Dimension ID: " + ordinal);
        } else {
            return CUSTOM;
        }
    }
}