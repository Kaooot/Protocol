package org.cloudburstmc.protocol.bedrock.data.gathering;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class GatheringJoinInfo {

    private UUID experienceID;
    private String experienceName;
    private UUID experienceWorldID;
    private String experienceWorldName;
    private String creatorID;
    /**
     * @since v944
     */
    private UUID ownerID; // owner id?
    /**
     * @since v944
     */
    private String scenarioID;
    /**
     * @since v944
     */
    private String serverID;
}