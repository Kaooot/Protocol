package org.cloudburstmc.protocol.bedrock.codec.v389.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.LegacyTelemetryEventSerializer_v388;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.data.event.HoneyHarvestedEventData;
import org.cloudburstmc.protocol.bedrock.data.event.PlayerDiedEventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v389 extends LegacyTelemetryEventSerializer_v388 {
    public static final LegacyTelemetryEventSerializer_v389 INSTANCE = new LegacyTelemetryEventSerializer_v389();

    protected LegacyTelemetryEventSerializer_v389() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.HONEY_HARVESTED, (b, h) -> HoneyHarvestedEventData.INSTANCE);
        this.writers.put(LegacyTelemetryEventPacket.Type.HONEY_HARVESTED, (b, h, e) -> {});
        this.writers.put(LegacyTelemetryEventPacket.Type.PLAYER_DIED,this::writePlayerDied);
        this.readers.put(LegacyTelemetryEventPacket.Type.PLAYER_DIED,this::readPlayerDied);
    }

    @Override
    protected PlayerDiedEventData readPlayerDied(ByteBuf buffer, BedrockCodecHelper helper) {
        int attackerEntityId = VarInts.readInt(buffer);
        int attackerVariant = VarInts.readInt(buffer);
        int entityDamageCause = VarInts.readInt(buffer);
        boolean inRaid = buffer.readBoolean();
        return new PlayerDiedEventData(attackerEntityId, attackerVariant, entityDamageCause, inRaid);
    }

    @Override
    protected void writePlayerDied(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        PlayerDiedEventData event = (PlayerDiedEventData) eventData;
        VarInts.writeInt(buffer, event.getInstigatorActorID());
        VarInts.writeInt(buffer, event.getInstigatorMobVariant());
        VarInts.writeInt(buffer, event.getDamageSource());
        buffer.writeBoolean(event.isDiedInRaid());
    }
}
