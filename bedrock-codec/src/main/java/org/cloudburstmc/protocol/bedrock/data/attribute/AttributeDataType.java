package org.cloudburstmc.protocol.bedrock.data.attribute;

public enum AttributeDataType {

    BOOL,
    FLOAT,
    COLOR,
    /**
     * @since v2259
     */
    STRING;

    private static final AttributeDataType[] VALUES = values();

    public static AttributeDataType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown AttributeDataType ID: " + ordinal);
    }
}
