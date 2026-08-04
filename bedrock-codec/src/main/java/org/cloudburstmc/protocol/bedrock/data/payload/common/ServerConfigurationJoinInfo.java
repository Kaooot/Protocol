package org.cloudburstmc.protocol.bedrock.data.payload.common;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.ClientStoreEntryPointConfiguration;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;

/**
 * @author Kaooot
 */
@Data
public class ServerConfigurationJoinInfo {

    private GatheringsConfigurationJoinInfo gatheringsConfig;
    private ClientStoreEntryPointConfiguration storeEntryPointInfo;
    private PresenceConfiguration presenceConfiguration;
}