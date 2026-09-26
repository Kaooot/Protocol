package org.cloudburstmc.protocol.bedrock.codec.v589.serializer;

import org.cloudburstmc.protocol.bedrock.codec.v471.serializer.LegacyTelemetryEventSerializer_v471;
import org.cloudburstmc.protocol.bedrock.data.event.Empty;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

public class LegacyTelemetryEventSerializer_v589 extends LegacyTelemetryEventSerializer_v471 {
    public static final LegacyTelemetryEventSerializer_v589 INSTANCE = new LegacyTelemetryEventSerializer_v589();

    protected LegacyTelemetryEventSerializer_v589() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.CAREFUL_RESTORATION, (buffer, helper) -> new Empty());
        this.writers.put(LegacyTelemetryEventPacket.Type.CAREFUL_RESTORATION, (buffer, helper, eventData) -> {
        });
    }
}
