package org.cloudburstmc.protocol.bedrock.data.player;

public enum PlayerPermissionLevel {

    VISITOR,
    MEMBER,
    OPERATOR,
    CUSTOM;

    private static final PlayerPermissionLevel[] VALUES = values();

    public static PlayerPermissionLevel from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlayerPermissionLevel ID: " + ordinal);
    }
}