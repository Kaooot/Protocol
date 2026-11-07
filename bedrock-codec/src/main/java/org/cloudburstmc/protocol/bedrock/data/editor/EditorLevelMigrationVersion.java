package org.cloudburstmc.protocol.bedrock.data.editor;

import java.lang.UnsupportedOperationException;

public enum EditorLevelMigrationVersion {

    LEGACY,
    EDITOR_ACHIEVEMENTS;

    private static final EditorLevelMigrationVersion[] VALUES = values();

    public static EditorLevelMigrationVersion from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown EditorLevelMigrationVersion ID: " + ordinal);
    }
}