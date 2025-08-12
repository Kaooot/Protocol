package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class RaidUpdateEventData implements EventData {
    private final int currentWave;
    private final int totalWaves;
    private final boolean success;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.RAID_UPDATE;
    }
}
