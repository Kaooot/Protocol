package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class FishBucketedEventData implements EventData {
    private final int pattern;
    private final int preset;
    private final int bucketedEntityType;
    private final boolean releaseEvent;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.FISH_BUCKETED_OBSOLETE;
    }
}
