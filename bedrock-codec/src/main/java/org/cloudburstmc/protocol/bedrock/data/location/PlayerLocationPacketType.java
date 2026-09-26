package org.cloudburstmc.protocol.bedrock.data.location;

public enum PlayerLocationPacketType {

    PLAYER_LOCATION_COORDINATES,
    PLAYER_LOCATION_HIDE;

    private static final PlayerLocationPacketType[] VALUES = values();

    public static PlayerLocationPacketType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlayerLocationPacketType ID: " + ordinal);
    }
}
