package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
public class RemoveScore {

    private ScoreboardId scoreboardId;
    private String objectiveName;
    @Deprecated
    @Removed("26.40")
    private int scoreValue;
}