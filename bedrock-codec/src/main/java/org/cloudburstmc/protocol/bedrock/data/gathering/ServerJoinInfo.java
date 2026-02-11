package org.cloudburstmc.protocol.bedrock.data.gathering;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class ServerJoinInfo {

    private GatheringJoinInfo gatheringJoinInfo;
    private StoreEntryPointInfo storeEntryPointInfo;
    private PresenceInfo presenceInfo;
}