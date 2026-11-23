package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;

@Data
public class ChangeEntityScore implements ScoreInfo {

    @Override
    public ScorePacketEntryAction getType() {
        return ScorePacketEntryAction.CHANGE_ENTITY;
    }


    private ScoreboardId scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private long actorId;
}