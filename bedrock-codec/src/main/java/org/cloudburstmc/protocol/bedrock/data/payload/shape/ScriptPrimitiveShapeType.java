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
    ARROW;

    private static final ScriptPrimitiveShapeType[] VALUES = values();

    public static ScriptPrimitiveShapeType from(int id) {
        return VALUES[id];
    }
}