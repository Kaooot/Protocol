package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum PlatformType {

    DESKTOP,
    CONSOLE,
    MOBILE;

    private static final PlatformType[] VALUES = values();

    public static PlatformType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlatformType ID: " + ordinal);
    }
}