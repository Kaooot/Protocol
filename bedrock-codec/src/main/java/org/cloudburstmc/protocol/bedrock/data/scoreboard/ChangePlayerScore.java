package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

@Data
public class ChangePlayerScore implements ScoreInfo {

    @Override
    public ScorePacketEntryAction getType() {
        return ScorePacketEntryAction.CHANGE_PLAYER;
    }


    private ScoreboardId scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private PlayerScoreboardId playerUniqueId;
}