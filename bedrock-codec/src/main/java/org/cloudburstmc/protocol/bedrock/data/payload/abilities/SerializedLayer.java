package org.cloudburstmc.protocol.bedrock.data.payload.abilities;

/**
 * @author Kaooot
 */
public enum SerializedLayer {

    CUSTOM_CACHE,
    BASE,
    SPECTATOR,
    COMMANDS,
    /**
     * @since v557
     */
    EDITOR,
    /**
     * @since v712
     */
    LOADING_SCREEN;

    private static final SerializedLayer[] VALUES = values();

    public static SerializedLayer from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown SerializedLayer ID: " + ordinal);
    }
}