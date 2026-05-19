package org.cloudburstmc.protocol.bedrock.data.payload.command;

/**
 * @author Kaooot
 */
public enum CommandBlockMode {

    NORMAL,
    REPEATING,
    CHAIN;

    private static final CommandBlockMode[] VALUES = values();

    public static CommandBlockMode from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown CommandBlockMode ID: " + ordinal);
    }
}