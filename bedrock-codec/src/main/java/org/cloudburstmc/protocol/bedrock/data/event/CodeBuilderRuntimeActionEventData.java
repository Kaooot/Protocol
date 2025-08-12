package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Data
public class CodeBuilderRuntimeActionEventData implements EventData {
    private final String codeBuilderRuntimeAction;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.CODE_BUILDER_RUNTIME_ACTION;
    }
}
