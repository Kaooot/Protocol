package org.cloudburstmc.protocol.bedrock.data.payload.scoreboard;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class RemoveScore implements ScoreInfo {

    private long scoreboardId;
    private String objectiveName;
    /**
     * @deprecated since v2168
     */
    private int scoreValue;

    @Override
    public ScorePacketEntryAction getAction() {
        return ScorePacketEntryAction.REMOVE;
    }
}