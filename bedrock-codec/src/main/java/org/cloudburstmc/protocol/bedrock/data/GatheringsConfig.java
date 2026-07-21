package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.UUID;
import lombok.Data;

@Data
public class GatheringsConfig {
  private UUID experienceId;

  private String experienceName;

  private UUID worldId;

  private String worldName;

  private String creatorId;

  private UUID targetId;

  private String scenarioId;

  private String serverId;
}
