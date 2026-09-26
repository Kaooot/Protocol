package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

@Data
public class MovementCorrected {

    private float positionDelta;
    private float cheatingScore;
    private float scoreThreshold;
    private float distanceThreshold;
    private int durationThreshold;
}