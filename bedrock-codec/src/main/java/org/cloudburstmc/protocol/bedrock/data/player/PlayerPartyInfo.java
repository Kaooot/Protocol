package org.cloudburstmc.protocol.bedrock.data.player;

import lombok.Data;

@Data
public class PlayerPartyInfo {

    private String partyId;
    private boolean isPartyLeader;
}