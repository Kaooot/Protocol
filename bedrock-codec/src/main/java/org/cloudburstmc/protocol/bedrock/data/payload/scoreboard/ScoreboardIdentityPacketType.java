package org.cloudburstmc.protocol.bedrock.data.payload.scoreboard;

/**
 * @author Kaooot
 */
public enum ScoreboardIdentityPacketType {

    UPDATE,
    REMOVE;

    private static final ScoreboardIdentityPacketType[] VALUES = values();

    public static ScoreboardIdentityPacketType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ScoreboardIdentityPacketType ID: " + ordinal);
    }
}