package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

@Data
public class ChangeFakePlayerScore {

    private ScoreboardId scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private String fakePlayerName;
}