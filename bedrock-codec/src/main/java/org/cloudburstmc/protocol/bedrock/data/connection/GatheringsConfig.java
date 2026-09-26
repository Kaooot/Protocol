package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.Data;

import java.util.UUID;

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