package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

/**
 * @author Kaooot
 */
public enum FloatAttributeOperation {

    OVERRIDE,
    ALPHA_BLEND,
    ADD,
    SUBTRACT,
    MULTIPLY,
    MINIMUM,
    MAXIMUM;

    private static final FloatAttributeOperation[] VALUES = values();

    public static FloatAttributeOperation from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown FloatAttributeOperation ID: " + ordinal);
    }
}