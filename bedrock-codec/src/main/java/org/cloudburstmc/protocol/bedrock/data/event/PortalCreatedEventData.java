package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class PortalCreatedEventData implements EventData {
    private DimensionType dimensionID;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PORTAL_CREATED;
    }
}
