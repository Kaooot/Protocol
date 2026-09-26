package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Edu only, telemetry data for the code builder scoreboard
 *
 * @since v471
 */
@Data
public class CodeBuilderScoreboard {

    private String objectiveName;
    private int score;
}