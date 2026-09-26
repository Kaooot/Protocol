package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.Data;

@Data
public class ServerConfig {

    private GatheringsConfig gathering;
    private ClientStoreEntryPointConfig clientStoreEntryPoint;
    private PresenceConfig presence;
}