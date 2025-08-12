package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgentCreatedEventData implements EventData {
    public static final AgentCreatedEventData INSTANCE = new AgentCreatedEventData();

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.AGENT_CREATED;
    }
}
