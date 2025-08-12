package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SneakCloseToSculkSensorEventData implements EventData {
    public static final SneakCloseToSculkSensorEventData INSTANCE = new SneakCloseToSculkSensorEventData();

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.SNEAK_CLOSE_TO_SCULK_SENSOR;
    }
}
