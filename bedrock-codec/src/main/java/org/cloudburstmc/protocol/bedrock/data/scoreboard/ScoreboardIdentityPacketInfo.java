package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

import java.util.UUID;

@Data
public class ScoreboardIdentityPacketInfo {

    private long scoreboardId;
    /**
     * @deprecated since v2168
     */
    private UUID uuid;
    /**
     * @since v2168
     */
    private long playerUniqueId;
}