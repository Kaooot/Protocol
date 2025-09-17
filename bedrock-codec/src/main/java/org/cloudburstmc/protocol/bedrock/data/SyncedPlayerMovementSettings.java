package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.Ignore;

/**
 * @author Kaooot
 */
@Docs
@Value
public class SyncedPlayerMovementSettings {

    /**
     * @deprecated since v818. {@link ServerAuthMovementMode#SERVER_AUTHORITATIVE_V3} is now the default movement mode.
     */
    @Ignore
    ServerAuthMovementMode authorityMode;
    @Docs(type = DataType.VARINT)
    int rewindHistorySize;
    boolean serverAuthoritativeBlockBreaking;
}