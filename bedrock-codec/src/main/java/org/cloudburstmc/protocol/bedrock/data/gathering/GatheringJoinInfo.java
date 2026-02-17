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
     * @since v941
     */
    private UUID unk;
    /**
     * @since v941
     */
    private UUID unk1;
    /**
     * @since v941
     */
    private String serverID;
}