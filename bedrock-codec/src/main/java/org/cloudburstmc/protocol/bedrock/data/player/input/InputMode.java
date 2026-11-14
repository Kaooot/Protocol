package org.cloudburstmc.protocol.bedrock.data.player.input;

public enum InputMode {

    UNDEFINED,
    MOUSE,
    TOUCH,
    GAME_PAD,
    /**
     * @deprecated since v859
     */
    MOTION_CONTROLLER,
    /**
     * @since v2259
     */
    GAME_PAD_AND_MOUSE,
    COUNT
}