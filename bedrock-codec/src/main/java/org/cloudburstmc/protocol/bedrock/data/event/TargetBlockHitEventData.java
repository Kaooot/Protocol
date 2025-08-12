package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Data
public class TargetBlockHitEventData implements EventData {
    private final int redstoneLevel;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.TARGET_BLOCK_HIT;
    }
}
