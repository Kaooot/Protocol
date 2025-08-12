package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class MovementCorrectedEventData implements EventData {
    private final float positionDelta;
    private final float cheatingScore;
    private final float scoreThreshold;
    private final float distanceThreshold;
    private final int durationThreshold;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_CORRECTED_OBSOLETE;
    }
}
