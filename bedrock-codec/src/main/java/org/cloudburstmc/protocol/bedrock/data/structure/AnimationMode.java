package org.cloudburstmc.protocol.bedrock.data.structure;

public enum AnimationMode {
    NONE,
    LAYERS,
    BLOCKS;

    private static final AnimationMode[] VALUES = AnimationMode.values();

    public static AnimationMode from(int id) {
        return VALUES[id];
    }
}
