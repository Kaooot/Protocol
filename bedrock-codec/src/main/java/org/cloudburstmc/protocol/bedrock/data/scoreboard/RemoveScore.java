package org.cloudburstmc.protocol.bedrock.data.scoreboard;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
public class RemoveScore implements ScoreInfo {

    @Override
    public ScorePacketEntryAction getType() {
        return ScorePacketEntryAction.REMOVE;
    }


    private ScoreboardId scoreboardId;
    private String objectiveName;
    @Deprecated
    @Removed("26.40")
    private int scoreValue;
}