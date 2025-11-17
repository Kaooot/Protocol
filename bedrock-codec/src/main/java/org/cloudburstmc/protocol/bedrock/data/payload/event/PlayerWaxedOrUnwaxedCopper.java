package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class PlayerWaxedOrUnwaxedCopper implements EventData {

    int playerWaxedOrUnwaxedCopperBlockID;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PLAYER_WAXED_OR_UNWAXED_COPPER;
    }
}