package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Long;
import java.lang.String;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandOriginData {
  private Type type;

  private UUID uuid;

  private String requestId;

  private Long playerId;
}
