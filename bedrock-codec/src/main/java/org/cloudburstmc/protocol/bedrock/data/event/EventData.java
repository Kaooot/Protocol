package org.cloudburstmc.protocol.bedrock.data.event;

import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

public interface EventData {

    LegacyTelemetryEventPacket.Type getType();
}
