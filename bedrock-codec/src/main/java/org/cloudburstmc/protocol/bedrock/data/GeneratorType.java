package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum GeneratorType {

    LEGACY,
    OVERWORLD,
    FLAT,
    NETHER,
    THE_END,
    VOID,
    UNDEFINED;

    private static final GeneratorType[] VALUES = values();

    public static GeneratorType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown GeneratorType ID: " + ordinal);
    }
}