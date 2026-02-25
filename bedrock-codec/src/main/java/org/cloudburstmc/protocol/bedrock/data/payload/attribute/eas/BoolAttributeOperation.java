package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

/**
 * @author Kaooot
 */
public enum BoolAttributeOperation {

    OVERRIDE,
    ALPHA_BLEND,
    AND,
    NAND,
    OR,
    NOR,
    XOR,
    XNOR;

    private static final BoolAttributeOperation[] VALUES = values();

    public static BoolAttributeOperation from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown BoolAttributeOperation ID: " + ordinal);
    }
}