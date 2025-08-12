package org.cloudburstmc.protocol.bedrock.codec.v671.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v589.serializer.LegacyTelemetryEventSerializer_v589;
import org.cloudburstmc.protocol.bedrock.data.event.InteractionEventData;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v671 extends LegacyTelemetryEventSerializer_v589 {
    public static final LegacyTelemetryEventSerializer_v671 INSTANCE = new LegacyTelemetryEventSerializer_v671();

    @Override
    protected InteractionEventData readInteract(ByteBuf buffer, BedrockCodecHelper helper) {
        long interactedEntityID = VarInts.readLong(buffer);
        int interactionType = VarInts.readInt(buffer);
        int interactionEntityType = VarInts.readInt(buffer);
        int entityVariant = VarInts.readInt(buffer);
        int entityColor = buffer.readUnsignedByte();
        return new InteractionEventData(interactedEntityID, interactionType, interactionEntityType, entityVariant, entityColor);
    }

    @Override
    protected void writeInteract(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        InteractionEventData event = (InteractionEventData) eventData;
        VarInts.writeLong(buffer, event.getInteractedEntityID());
        VarInts.writeInt(buffer, event.getInteractionType());
        VarInts.writeInt(buffer, event.getInteractionActorType());
        VarInts.writeInt(buffer, event.getInteractionActorVariant());
        buffer.writeByte(event.getInteractionActorColor());
    }
}