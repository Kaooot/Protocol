package org.cloudburstmc.protocol.bedrock.data;

public enum InputMode {
    UNDEFINED,
    MOUSE,
    TOUCH,
    GAME_PAD,
    COUNT;

    private static final InputMode[] VALUES = values();

    public static InputMode from(int id) {
        return VALUES[id];
    }
}
