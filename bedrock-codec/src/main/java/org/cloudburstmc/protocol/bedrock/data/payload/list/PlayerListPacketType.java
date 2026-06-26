package org.cloudburstmc.protocol.bedrock.data.payload.list;

/**
 * @author Kaooot
 */
public enum PlayerListPacketType {

    REMOVE,
    ADD;

    private static final PlayerListPacketType[] VALUES = values();

    public static PlayerListPacketType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlayerListPacketType ID: " + ordinal);
    }
}