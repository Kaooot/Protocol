package org.cloudburstmc.protocol.bedrock.data.payload.editor;

/**
 * @author Kaooot
 */
public enum ServerEditorConnectionPolicy {

    MATCH_WORLD_TYPE,
    EDITOR_ONLY,
    VANILLA_ONLY,
    MIXED;

    private static final ServerEditorConnectionPolicy[] VALUES = values();

    public static ServerEditorConnectionPolicy from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ServerEditorConnectionPolicy ID: " + ordinal);
    }
}