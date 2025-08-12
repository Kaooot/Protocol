package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class PatternRemovedEventData implements EventData {
    private final int itemId;
    private final int auxValue;
    private final int patternsSize;
    private final int patternIndex;
    private final int patternColor;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PATTERN_REMOVED_OBSOLETE;
    }
}
