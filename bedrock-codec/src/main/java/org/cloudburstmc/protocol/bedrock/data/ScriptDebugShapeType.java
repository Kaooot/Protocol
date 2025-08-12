package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum ScriptDebugShapeType {
    LINE,
    BOX,
    SPHERE,
    CIRCLE,
    TEXT,
    ARROW;

    private static final ScriptDebugShapeType[] VALUES = values();

    public static ScriptDebugShapeType from(int id) {
        return VALUES[id];
    }
}