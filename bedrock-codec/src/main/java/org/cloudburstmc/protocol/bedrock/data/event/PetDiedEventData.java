package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

@Value
public class PetDiedEventData implements EventData {
    private final boolean ownerKilled;
    private final long killerUniqueEntityId;
    private final long petUniqueEntityId;
    private final int entityDamageCause;
    private final int petEntityType;

    @Override
    public LegacyTelemetryEventPacket.Type getType() {
        return LegacyTelemetryEventPacket.Type.PET_DIED_OBSOLETE;
    }
}
