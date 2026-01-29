package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class GatheringJoinInfo {

    String experienceID;
    String experienceName;
    String experienceWorldID;
    String experienceWorldName;
    String creatorID;
    String storeID;
}