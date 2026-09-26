package org.cloudburstmc.protocol.bedrock.codec.v389.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.LegacyTelemetryEventSerializer_v388;
import org.cloudburstmc.protocol.bedrock.data.event.Empty;
import org.cloudburstmc.protocol.bedrock.data.event.PlayerDied;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v389 extends LegacyTelemetryEventSerializer_v388 {
    public static final LegacyTelemetryEventSerializer_v389 INSTANCE = new LegacyTelemetryEventSerializer_v389();

    protected LegacyTelemetryEventSerializer_v389() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.HONEY_HARVESTED, (buffer, helper) -> new Empty());
        this.writers.put(LegacyTelemetryEventPacket.Type.HONEY_HARVESTED, (buffer, helper, eventData) -> {
        });
    }

    @Override
    protected PlayerDied readPlayerDied(ByteBuf buffer, BedrockCodecHelper helper) {
        PlayerDied event = new PlayerDied();
        event.setInstigatorActorID(VarInts.readInt(buffer));
        event.setInstigatorMobVariant(VarInts.readInt(buffer));
        event.setDamageSource(VarInts.readInt(buffer));
        event.setDiedInRaid(buffer.readBoolean());
        return event;
    }

    @Override
    protected void writePlayerDied(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PlayerDied event = (PlayerDied) eventData;
        VarInts.writeInt(buffer, event.getInstigatorActorID());
        VarInts.writeInt(buffer, event.getInstigatorMobVariant());
        VarInts.writeInt(buffer, event.getDamageSource());
        buffer.writeBoolean(event.isDiedInRaid());
    }
}
