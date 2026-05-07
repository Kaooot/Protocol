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

    private static final ScriptPrimitiveShapeType[] VALUES = values();

    public static ScriptPrimitiveShapeType from(int id) {
        return VALUES[id];
    }
}