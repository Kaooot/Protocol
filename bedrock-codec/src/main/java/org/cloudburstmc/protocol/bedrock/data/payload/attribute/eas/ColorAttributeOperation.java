package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

/**
 * @author Kaooot
 */
public enum ColorAttributeOperation {

    OVERRIDE,
    ALPHA_BLEND,
    ADD,
    SUBTRACT,
    MULTIPLY;

    private static final ColorAttributeOperation[] VALUES = values();

    public static ColorAttributeOperation from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ColorAttributeOperation ID: " + ordinal);
    }
}