package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class ServerConfig {
  private GatheringsConfig gathering;

  private ClientStoreEntryPointConfig clientStoreEntryPoint;

  private PresenceConfig presence;
}
