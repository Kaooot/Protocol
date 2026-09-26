package org.cloudburstmc.protocol.bedrock.data.player.list;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerListPacketType {

    REMOVE(1),
    ADD(0);

    private final int legacyId;

    private static final PlayerListPacketType[] VALUES = values();

    public static PlayerListPacketType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown PlayerListPacketType ID: " + ordinal);
    }

    public static PlayerListPacketType fromLegacy(int ordinal) {
        for (final PlayerListPacketType value : VALUES) {
            if (value.getLegacyId() == ordinal) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown PlayerListPacketType ID: " + ordinal);
    }
}
