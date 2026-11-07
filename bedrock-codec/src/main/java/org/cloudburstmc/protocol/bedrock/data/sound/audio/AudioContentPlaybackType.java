package org.cloudburstmc.protocol.bedrock.data.sound.audio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AudioContentPlaybackType {

    MUSIC,
    SOUND;

    private static final AudioContentPlaybackType[] VALUES = values();

    public static AudioContentPlaybackType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown AudioContentPlaybackType ID: " + ordinal);
    }
}