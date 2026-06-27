package org.cloudburstmc.protocol.bedrock.data.payload.scoreboard;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class ChangeFakePlayerScore implements ScoreInfo {

    private long scoreboardId;
    private String objectiveName;
    private int scoreValue;
    private String fakePlayerName;

    @Override
    public ScorePacketEntryAction getAction() {
        return ScorePacketEntryAction.CHANGE_FAKE_PLAYER;
    }
}