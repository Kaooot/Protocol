package org.cloudburstmc.protocol.bedrock.data.connection.matchmaking;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MatchmakingState {

    IDLE,
    MATCHMAKING,
    MATCH_FOUND,
    CANCELED,
    PLAYER_LEFT_PARTY,
    PLAYER_LEFT_SERVER,
    SERVER_SHUTDOWN,
    TIMED_OUT,
    REQUEUE_AS_PARTY;

    private static final MatchmakingState[] VALUES = values();

    public static MatchmakingState from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown MatchmakingState ID: " + ordinal);
    }
}