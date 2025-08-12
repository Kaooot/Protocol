package org.cloudburstmc.protocol.bedrock.data.structure;

public enum Rotation {
    NONE,
    ROTATE_90,
    ROTATE_180,
    ROTATE_270;

    private static final Rotation[] VALUES = Rotation.values();

    public static Rotation from(int id) {
        return VALUES[id];
    }
}
