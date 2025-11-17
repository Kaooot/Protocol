package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class POICauldronUsed implements EventData {

    MinecraftEventing.POIBlockInteractionType blockInteractionType;
    short itemId;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.POI_CAULDRON_USED;
    }
}