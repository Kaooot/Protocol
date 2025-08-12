package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StriderRiddenInLavaInOverworldEventData implements EventData {
    public static final StriderRiddenInLavaInOverworldEventData INSTANCE = new StriderRiddenInLavaInOverworldEventData();

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD;
    }
}
