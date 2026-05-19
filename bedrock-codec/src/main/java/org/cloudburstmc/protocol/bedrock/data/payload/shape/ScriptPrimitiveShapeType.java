package org.cloudburstmc.protocol.bedrock.data.payload.shape;

/**
 * @author Kaooot
 */
public enum ScriptPrimitiveShapeType {
    LINE,
    BOX,
    SPHERE,
    CIRCLE,
    TEXT,
    ARROW,
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

    private static final ScriptPrimitiveShapeType[] VALUES = values();

    public static ScriptPrimitiveShapeType from(int id) {
        return VALUES[id];
    }
}