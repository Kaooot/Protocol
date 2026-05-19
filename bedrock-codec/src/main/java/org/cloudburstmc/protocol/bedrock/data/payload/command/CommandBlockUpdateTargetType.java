package org.cloudburstmc.protocol.bedrock.data.payload.command;

/**
 * @author Kaooot
 */
public enum CommandBlockUpdateTargetType {

    ENTITY,
    BLOCK;

    private static final CommandBlockUpdateTargetType[] VALUES = values();

    public static CommandBlockUpdateTargetType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown CommandBlockUpdateTargetType ID: " + ordinal);
    }
}