package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class BossKilledEventData implements EventData {
    private final long bossActorID;
    private final int partySize;
    private final int bossType;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.BOSS_KILLED;
    }
}
