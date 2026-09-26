package org.cloudburstmc.protocol.bedrock.data.player.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncedPlayerMovementSettings {

    /**
     * @deprecated since v818. {@link ServerAuthMovementMode#SERVER_AUTHORITATIVE_V3} is now the default movement mode.
     */
    private ServerAuthMovementMode authorityMode;
    private int rewindHistorySize;
    private boolean serverAuthoritativeBlockBreaking;
}