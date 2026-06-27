package org.cloudburstmc.protocol.bedrock.data.payload.scoreboard;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class ScoreboardIdentityPacketInfo {

    private long scoreboardId;
    /**
     * @deprecated since v2164
     */
    private UUID uuid;
    /**
     * @since v2164
     */
    private long playerUniqueId;
}