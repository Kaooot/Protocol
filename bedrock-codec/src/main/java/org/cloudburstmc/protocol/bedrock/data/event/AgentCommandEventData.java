package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class AgentCommandEventData implements EventData {
    private final LegacyTelemetryEventPacket.AgentResult result;
    private final String command;
    private final String dataKey;
    private final int dataValue;
    private final String output;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.AGENT_COMMAND_OBSOLETE;
    }
}
