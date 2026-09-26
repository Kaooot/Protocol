package org.cloudburstmc.protocol.bedrock.data.sound;

public enum SoundDataEvent {

    STOP,
    SET_VOLUME,
    SET_PITCH,
    FADE,
    SEEK_TO,
    PAUSE,
    RESUME;

    public static final SoundDataEvent[] VALUES = values();

    public static SoundDataEvent from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown SoundDataEvent ID: " + ordinal);
    }
}