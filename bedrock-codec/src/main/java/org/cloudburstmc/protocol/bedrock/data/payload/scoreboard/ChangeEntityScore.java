package org.cloudburstmc.protocol.bedrock.data.payload.scoreboard;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class ChangeEntityScore implements ScoreInfo {

    private long scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private long actorId;

    @Override
    public ScorePacketEntryAction getAction() {
        return ScorePacketEntryAction.CHANGE_ENTITY;
    }
}