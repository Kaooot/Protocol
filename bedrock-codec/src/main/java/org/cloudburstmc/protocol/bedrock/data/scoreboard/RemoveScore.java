package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

@Data
public class RemoveScore {

    private ScoreboardId scoreboardId;
    private String objectiveName;
    /**
     * @deprecated since v2168
     */
    private int scoreValue;
}