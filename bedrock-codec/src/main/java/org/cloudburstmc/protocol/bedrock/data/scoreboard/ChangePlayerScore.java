package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

@Data
public class ChangePlayerScore {

    private ScoreboardId scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private PlayerScoreboardId playerUniqueId;
}