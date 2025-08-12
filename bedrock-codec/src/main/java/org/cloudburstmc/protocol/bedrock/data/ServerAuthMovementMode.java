package org.cloudburstmc.protocol.bedrock.data;

import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;

/**
 * The authoritative movement mode chosen by the server in the {@link StartGamePacket} to verify the clients movement is
 * valid.
 */
public enum ServerAuthMovementMode {
    /**
     * Movement is completely controlled by the client and does not send {@link PlayerAuthInputPacket}
     *
     * @deprecated v800
     */
    LEGACY_CLIENT_AUTHORITATIVE_V1_DEPRECATED,
    /**
     * Movement is verified by the server using the {@link PlayerAuthInputPacket}
     */
    CLIENT_AUTHORITATIVE_V2,
    SERVER_AUTHORITATIVE_V3;

    private static final ServerAuthMovementMode[] VALUES = values();

    public static ServerAuthMovementMode from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown AuthoritativeMovementMode ID: " + ordinal);
    }
}
