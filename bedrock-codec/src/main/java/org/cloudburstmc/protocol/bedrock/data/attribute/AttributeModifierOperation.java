package org.cloudburstmc.protocol.bedrock.data.attribute;

public enum AttributeModifierOperation {

    OPERATION_ADDITION,
    OPERATION_MULTIPLY_BASE,
    OPERATION_MULTIPLY_TOTAL,
    OPERATION_CAP,
    OPERATION_INVALID;

    private static final AttributeModifierOperation[] VALUES = values();

    public static AttributeModifierOperation from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown AttributeModifierOperation ID: " + ordinal);
    }
}