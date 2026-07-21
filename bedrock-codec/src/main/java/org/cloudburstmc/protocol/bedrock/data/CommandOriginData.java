package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import java.lang.String;
import java.util.UUID;
import lombok.Data;

@Data
public class CommandOriginData {
  private Type type;

  private UUID uuid;

  private String requestId;

  private Long playerId;
}
