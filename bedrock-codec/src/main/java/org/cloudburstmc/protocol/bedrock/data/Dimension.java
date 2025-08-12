package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum Dimension {

    OVERWORLD,
    NETHER,
    THE_END,
    UNDEFINED;

    private static final Dimension[] VALUES = values();

    public static Dimension from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown Dimension ID: " + ordinal);
    }
}