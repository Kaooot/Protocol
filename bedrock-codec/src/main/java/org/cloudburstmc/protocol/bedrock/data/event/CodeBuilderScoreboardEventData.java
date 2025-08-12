package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Data
public class CodeBuilderScoreboardEventData implements EventData {
    private final String objectiveName;
    private final int score;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.CODE_BUILDER_SCOREBOARD;
    }
}
