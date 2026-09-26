package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

@Data
public class MovementAnomaly {

    private int eventType;
    private float cheatingScore;
    private float averagePositionDelta;
    private float totalPositionDelta;
    private float minPositionDelta;
    private float maxPositionDelta;
}