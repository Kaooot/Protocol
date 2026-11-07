package org.cloudburstmc.protocol.bedrock.data.connection.matchmaking;

import lombok.Data;

@Data
public class MatchmakingStateOptions {

    private String triggeringPlayerName;
    private boolean triggeredByLocalPlayer;
}