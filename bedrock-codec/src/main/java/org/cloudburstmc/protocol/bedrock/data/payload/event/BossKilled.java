package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class BossKilled implements EventData {

    long bossActorID;
    int partySize;
    int bossType;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.BOSS_KILLED;
    }
}