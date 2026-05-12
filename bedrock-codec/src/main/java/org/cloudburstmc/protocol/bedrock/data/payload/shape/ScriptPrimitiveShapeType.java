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
     * @since v998
     */
    CYLINDER,
    /**
     * @since v998
     */
    PYRAMID,
    /**
     * @since v998
     */
    ELLIPSOID,
    /**
     * @since v998
     */
    CONE;

    private static final ScriptPrimitiveShapeType[] VALUES = values();

    public static ScriptPrimitiveShapeType from(int id) {
        return VALUES[id];
    }
}