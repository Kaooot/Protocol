package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

import java.util.List;

@Value
public class SlashCommandEventData implements EventData {
    private final String commandName;
    private final int successCount;
    private final List<String> outputMessages;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.SLASH_COMMAND;
    }
}
