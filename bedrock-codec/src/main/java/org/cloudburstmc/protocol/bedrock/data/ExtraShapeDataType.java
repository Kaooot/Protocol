package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum ExtraShapeDataType {

    NONE,
    ARROW,
    TEXT,
    BOX,
    LINE,
    SPHERE,
    /**
     * @since v1001
     */
    CYLINDER,
    /**
     * @since v1001
     */
    PYRAMID,
    /**
     * @since v1001
     */
    ELLIPSOID,
    /**
     * @since v1001
     */
    CONE;

    private static final ExtraShapeDataType[] VALUES = values();

    public static ExtraShapeDataType from(int ordinal) {
        if (ordinal >= VALUES.length || ordinal < 0) {
            throw new UnsupportedOperationException("Detected unknown ExtraShapeDataType ID: " + ordinal);
        }
        return VALUES[ordinal];
    }
}