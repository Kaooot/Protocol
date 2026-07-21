package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerConfig {
  private GatheringsConfig gathering;

  private ClientStoreEntryPointConfig clientStoreEntryPoint;

  private PresenceConfig presence;
}
