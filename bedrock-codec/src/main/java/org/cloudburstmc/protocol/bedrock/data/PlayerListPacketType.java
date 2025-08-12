package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum PlayerListPacketType {

    ADD,
    REMOVE;

    private static final PlayerListPacketType[] VALUES = values();

    public static PlayerListPacketType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlayerListPacketType ID: " + ordinal);
    }
}