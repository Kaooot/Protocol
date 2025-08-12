package org.cloudburstmc.protocol.bedrock.codec.v589.serializer;

import org.cloudburstmc.protocol.bedrock.codec.v471.serializer.LegacyTelemetryEventSerializer_v471;
import org.cloudburstmc.protocol.bedrock.data.event.CarefulRestorationEventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

public class LegacyTelemetryEventSerializer_v589 extends LegacyTelemetryEventSerializer_v471 {
    public static final LegacyTelemetryEventSerializer_v589 INSTANCE = new LegacyTelemetryEventSerializer_v589();

    public LegacyTelemetryEventSerializer_v589() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.CAREFUL_RESTORATION, (b, h) -> CarefulRestorationEventData.INSTANCE);
        this.writers.put(LegacyTelemetryEventPacket.Type.CAREFUL_RESTORATION, (b, h, e) -> {});
    }
}
