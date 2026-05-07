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
     * @since v990
     */
    CYLINDER,
    /**
     * @since v990
     */
    PYRAMID,
    /**
     * @since v990
     */
    ELLIPSOID,
    /**
     * @since v990
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