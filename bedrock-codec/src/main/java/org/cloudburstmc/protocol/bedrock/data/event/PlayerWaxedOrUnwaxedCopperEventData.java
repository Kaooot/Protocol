package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Data
public class PlayerWaxedOrUnwaxedCopperEventData implements EventData {
    private final BlockDefinition definition;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PLAYER_WAXED_OR_UNWAXED_COPPER;
    }
}
