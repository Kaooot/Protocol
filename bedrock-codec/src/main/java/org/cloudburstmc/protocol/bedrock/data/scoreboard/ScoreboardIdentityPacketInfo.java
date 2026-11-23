package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

import java.util.UUID;

@Data
public class ScoreboardIdentityPacketInfo {

    private long scoreboardId;
    @Deprecated
    @Removed("26.40")
    private UUID uuid;
    @Added("26.40")
    private long playerUniqueId;
}