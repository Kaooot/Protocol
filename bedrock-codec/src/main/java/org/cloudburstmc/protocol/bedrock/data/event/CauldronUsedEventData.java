package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class CauldronUsedEventData implements EventData {
    private final int contentsColor;
    private final int contentsType;
    private final int fillLevel;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.CAULDRON_USED;
    }
}
