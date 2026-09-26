package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

@Data
public class ChangeEntityScore {

    private ScoreboardId scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private long actorId;
}