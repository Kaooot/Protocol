package org.cloudburstmc.protocol.bedrock.data.gathering;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class GatheringsConfig {

    private UUID experienceId;
    private String experienceName;
    private UUID worldId;
    private String worldName;
    private String creatorId;
    /**
     * @since v944
     */
    private UUID targetId;
    /**
     * @since v944
     */
    private String scenarioId;
    /**
     * @since v944
     */
    private String serverId;
}