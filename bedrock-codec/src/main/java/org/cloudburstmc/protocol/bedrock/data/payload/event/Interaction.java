package org.cloudburstmc.protocol.bedrock.data.payload.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

/**
 * @author Kaooot
 */
@Value
public class Interaction implements EventData {

    long interactedEntityID;
    MinecraftEventing.InteractionType interactionType;
    int interactionActorType;
    int interactionActorVariant;
    int interactionActorColor;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.INTERACTION;
    }
}