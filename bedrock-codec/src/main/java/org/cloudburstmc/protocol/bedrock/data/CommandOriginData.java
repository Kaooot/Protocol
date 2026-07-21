package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import java.lang.String;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandOriginData {
  private Type type;

  private UUID uuid;

  private String requestId;

  private Long playerId;
}
