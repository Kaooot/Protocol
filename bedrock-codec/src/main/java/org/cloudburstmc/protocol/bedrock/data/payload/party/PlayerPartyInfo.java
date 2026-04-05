package org.cloudburstmc.protocol.bedrock.data.payload.party;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class PlayerPartyInfo {

    private String partyId;
    private boolean isPartyLeader;
}