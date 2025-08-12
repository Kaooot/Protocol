package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class PlayerDiedEventData implements EventData {
    private final int instigatorActorID;
    private final int instigatorMobVariant;
    private final int damageSource;
    private final boolean diedInRaid;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PLAYER_DIED;
    }
}