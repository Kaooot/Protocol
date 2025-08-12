package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Data
public class PiglinBarterEventData implements EventData {
    private final ItemDefinition definition;
    private final boolean wasTargetingBarteringPlayer;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PIGLIN_BARTER;
    }
}
