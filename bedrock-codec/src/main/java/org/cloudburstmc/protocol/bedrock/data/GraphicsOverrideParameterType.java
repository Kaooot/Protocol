package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum GraphicsOverrideParameterType {
    /**
     * Sent to set the sky zenith color
     */
    SKY_ZENITH_COLOR;

    private static final GraphicsOverrideParameterType[] VALUES = values();

    public static GraphicsOverrideParameterType from(int ordinal) {
        return VALUES[ordinal];
    }
}