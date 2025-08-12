package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
@RequiredArgsConstructor
@AllArgsConstructor
public class MobKilledEventData implements EventData {
    private final long instigatorActorID;
    private final long targetActorID;
    private final int instigatorsCHildActorType;
    private final int damageSource;
    @NonFinal
    private int tradeTier = -1;
    @NonFinal
    private String traderName = "";

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.MOB_KILLED;
    }
}
