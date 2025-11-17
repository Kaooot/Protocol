package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class TargetBlockHit implements EventData {

    int redstoneLevel;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.TARGET_BLOCK_HIT;
    }
}