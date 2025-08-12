package org.cloudburstmc.protocol.bedrock.codec.v354.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v340.serializer.LegacyTelemetryEventSerializer_v340;
import org.cloudburstmc.protocol.bedrock.data.BlockInteractionType;
import org.cloudburstmc.protocol.bedrock.data.event.*;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v354 extends LegacyTelemetryEventSerializer_v340 {
    public static final LegacyTelemetryEventSerializer_v354 INSTANCE = new LegacyTelemetryEventSerializer_v354();

    protected LegacyTelemetryEventSerializer_v354() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.POI_CAULDRON_USED, this::readPOICauldronUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.COMPOSTER_USED, this::readComposterUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.BELL_USED, this::readBellUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.POI_CAULDRON_USED, this::writePOICauldronUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.COMPOSTER_USED, this::writeComposterUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.BELL_USED, this::writeBellUsed);
    }

    protected POICauldronUsedEventData readPOICauldronUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        BlockInteractionType type = BlockInteractionType.values()[VarInts.readInt(buffer)];
        int itemId = VarInts.readInt(buffer);
        return new POICauldronUsedEventData(type, itemId);
    }

    protected void writePOICauldronUsed(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        POICauldronUsedEventData event = (POICauldronUsedEventData) eventData;
        VarInts.writeInt(buffer, event.getBlockInteractionType().ordinal());
        VarInts.writeInt(buffer, event.getItemId());
    }

    protected ComposterUsedEventData readComposterUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        BlockInteractionType type = BlockInteractionType.values()[VarInts.readInt(buffer)];
        int itemId = VarInts.readInt(buffer);
        return new ComposterUsedEventData(type, itemId);
    }

    protected void writeComposterUsed(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        ComposterUsedEventData event = (ComposterUsedEventData) eventData;
        VarInts.writeInt(buffer, event.getBlockInteractionType().ordinal());
        VarInts.writeInt(buffer, event.getItemId());
    }

    protected BellUsedEventData readBellUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        int itemId = VarInts.readInt(buffer);
        return new BellUsedEventData(itemId);
    }

    protected void writeBellUsed(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        BellUsedEventData event = (BellUsedEventData) eventData;
        VarInts.writeInt(buffer, event.getItemId());
    }
}
