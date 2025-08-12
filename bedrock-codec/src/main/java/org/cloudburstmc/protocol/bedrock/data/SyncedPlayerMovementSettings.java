package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class SyncedPlayerMovementSettings {

    /**
     * @deprecated since v818. {@link ServerAuthMovementMode#SERVER_AUTHORITATIVE_V3} is now the default movement mode.
     */
    ServerAuthMovementMode authorityMode;
    int rewindHistorySize;
    boolean serverAuthoritativeBlockBreaking;
}