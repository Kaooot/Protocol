package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

@Data
public class ChangeFakePlayerScore implements ScoreInfo {

    @Override
    public ScorePacketEntryAction getType() {
        return ScorePacketEntryAction.CHANGE_FAKE_PLAYER;
    }


    private ScoreboardId scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private String fakePlayerName;
}