package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum MemoryTier {

    SUPER_LOW,
    LOW,
    MID,
    HIGH,
    SUPER_HIGH;

    private static final MemoryTier[] VALUES = values();

    public static MemoryTier from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown MemoryTier ID: " + ordinal);
    }
}