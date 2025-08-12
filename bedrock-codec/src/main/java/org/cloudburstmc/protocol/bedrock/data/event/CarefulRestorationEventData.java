package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarefulRestorationEventData implements EventData {
    public static final CarefulRestorationEventData INSTANCE = new CarefulRestorationEventData();

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.CAREFUL_RESTORATION;
    }
}
