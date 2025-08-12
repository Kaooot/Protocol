package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class MovementAnomalyEventData implements EventData {
    private final int eventType;
    private final float cheatingScore;
    private final float averagePositionDelta;
    private final float totalPositionDelta;
    private final float minPositionDelta;
    private final float maxPositionDelta;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_ANOMALY_OBSOLETE;
    }
}
