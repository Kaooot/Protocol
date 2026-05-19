package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

/**
 * @author Kaooot
 */
public enum AttributeOperands {

    OPERAND_MIN,
    OPERAND_MAX,
    OPERAND_CURRENT,
    OPERAND_INVALID;

    private static final AttributeOperands[] VALUES = values();

    public static AttributeOperands from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown AttributeOperands ID: " + ordinal);
    }
}