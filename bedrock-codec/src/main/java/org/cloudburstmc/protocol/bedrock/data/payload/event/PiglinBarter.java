package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class PiglinBarter implements EventData {

    int itemId;
    boolean wasTargetingBarteringPlayer;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PIGLIN_BARTER;
    }
}