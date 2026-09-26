package org.cloudburstmc.protocol.bedrock.codec.v671.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v589.serializer.LegacyTelemetryEventSerializer_v589;
import org.cloudburstmc.protocol.bedrock.data.event.Interaction;
import org.cloudburstmc.protocol.bedrock.data.event.InteractionType;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v671 extends LegacyTelemetryEventSerializer_v589 {
    public static final LegacyTelemetryEventSerializer_v671 INSTANCE = new LegacyTelemetryEventSerializer_v671();

    protected LegacyTelemetryEventSerializer_v671() {
        super();
    }

    @Override
    protected Interaction readInteraction(ByteBuf buffer, BedrockCodecHelper helper) {
        Interaction event = new Interaction();
        event.setInteractedEntityID(VarInts.readLong(buffer));
        event.setInteractionType(InteractionType.from(VarInts.readInt(buffer)));
        event.setInteractionActorType(VarInts.readInt(buffer));
        event.setInteractionActorVariant(VarInts.readInt(buffer));
        event.setInteractionActorColor(buffer.readUnsignedByte());
        return event;
    }

    @Override
    protected void writeInteraction(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        Interaction event = (Interaction) eventData;
        VarInts.writeLong(buffer, event.getInteractedEntityID());
        VarInts.writeInt(buffer, event.getInteractionType().ordinal());
        VarInts.writeInt(buffer, event.getInteractionActorType());
        VarInts.writeInt(buffer, event.getInteractionActorVariant());
        buffer.writeByte(event.getInteractionActorColor());
    }
}
