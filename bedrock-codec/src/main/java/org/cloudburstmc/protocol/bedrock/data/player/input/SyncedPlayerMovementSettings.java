package org.cloudburstmc.protocol.bedrock.data.player.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncedPlayerMovementSettings {

    /**
     * {@link ServerAuthMovementMode#SERVER_AUTHORITATIVE_V3} is now the default movement mode.
     */
    @Deprecated
    @Removed("1.21.90")
    private ServerAuthMovementMode authorityMode;
    private int rewindHistorySize;
    private boolean serverAuthoritativeBlockBreaking;
}