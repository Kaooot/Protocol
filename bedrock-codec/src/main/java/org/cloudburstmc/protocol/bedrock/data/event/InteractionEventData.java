package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class InteractionEventData implements EventData {
    /**
     * @since v671
     */
    private final long interactedEntityID;
    private final int interactionType;
    private final int interactionActorType;
    private final int interactionActorVariant;
    private final int interactionActorColor;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.INTERACTION;
    }
}
