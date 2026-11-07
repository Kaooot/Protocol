package org.cloudburstmc.protocol.bedrock.data.sound;

public enum SoundDataEventType {

    STOP,
    SET_VOLUME,
    SET_PITCH,
    FADE,
    SEEK_TO,
    PAUSE,
    RESUME;

    public static final SoundDataEventType[] VALUES = values();

    public static SoundDataEventType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown SoundDataEventType ID: " + ordinal);
    }
}