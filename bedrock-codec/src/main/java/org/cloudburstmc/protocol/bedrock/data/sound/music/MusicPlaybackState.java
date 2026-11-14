package org.cloudburstmc.protocol.bedrock.data.sound.music;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum MusicPlaybackState {

    LOCAL("local"),
    ONLINE("online"),
    NOT_FOUND("notfound"),
    PENDING("pending");

    private static final Map<String, MusicPlaybackState> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (MusicPlaybackState value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static MusicPlaybackState fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }
}