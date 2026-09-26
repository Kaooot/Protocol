package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum MatchmakingState {

    IDLE("idle"),
    MATCHMAKING("matchmaking"),
    MATCH_FOUND("matchfound");

    private static final Map<String, MatchmakingState> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (MatchmakingState value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static MatchmakingState fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }
}