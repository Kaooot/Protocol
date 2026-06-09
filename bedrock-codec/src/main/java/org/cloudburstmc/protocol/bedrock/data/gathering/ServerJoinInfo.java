package org.cloudburstmc.protocol.bedrock.data.gathering;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.ClientStoreEntryPointConfiguration;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;

/**
 * @author Kaooot
 */
@Data
public class ServerJoinInfo {

    private GatheringsConfig gatheringsConfig;
    private ClientStoreEntryPointConfiguration storeEntryPointInfo;
    private PresenceConfiguration presenceConfiguration;
}