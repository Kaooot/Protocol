package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Edu only, telemetry data for the code builder scoreboard
 */
@Data
@Added("1.17.40")
public class CodeBuilderScoreboard {

    private String objectiveName;
    private int score;
}