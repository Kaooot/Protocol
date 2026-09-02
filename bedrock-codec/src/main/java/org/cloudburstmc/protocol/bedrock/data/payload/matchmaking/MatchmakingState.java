package org.cloudburstmc.protocol.bedrock.data.payload.matchmaking;

/**
 * @author Kaooot
 * @since v2207
 */
public enum MatchmakingState {

    IDLE,
    MATCHMAKING,
    MATCH_FOUND;

    private static final MatchmakingState[] VALUES = values();

    public static MatchmakingState from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new IllegalArgumentException("Unknown MatchmakingState: " + ordinal);
    }
}
