package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class MobKilled implements EventData {

    long instigatorActorID;
    long targetActorID;
    int instigatorsChildActorType;
    int damageSource;
    int tradeTier;
    String traderName;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.MOB_KILLED;
    }
}