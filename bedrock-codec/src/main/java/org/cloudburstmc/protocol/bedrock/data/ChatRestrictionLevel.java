package org.cloudburstmc.protocol.bedrock.data;

public enum ChatRestrictionLevel {
    /**
     * Default behaviour.
     */
    NONE,
    /**
     * The chat window will appear, but all messages will be dropped.
     */
    DROPPED,
    /**
     * The chat window will not show at all, unless you have the
     * {@link PlayerPermissionLevel#OPERATOR} permission set in your abilities.
     */
    DISABLED;

    private static final ChatRestrictionLevel[] VALUES = values();

    public static ChatRestrictionLevel from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ChatRestrictionLevel ID: " + ordinal);
    }
}
