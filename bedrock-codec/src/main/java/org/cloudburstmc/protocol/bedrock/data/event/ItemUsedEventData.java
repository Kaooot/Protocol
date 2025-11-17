package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class ItemUsedEventData implements EventData {
    private final short itemId;
    private final int itemAux;
    private final int useMethod;
    private final int count;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.ITEM_USED;
    }
}