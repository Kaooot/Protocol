package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class SlashCommand implements EventData {

    int successCount;
    int errorCount;
    String commandName;
    String errorList;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.SLASH_COMMAND;
    }
}