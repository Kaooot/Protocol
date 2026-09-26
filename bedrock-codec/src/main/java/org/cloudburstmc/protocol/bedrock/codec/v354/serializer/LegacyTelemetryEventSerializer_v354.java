package org.cloudburstmc.protocol.bedrock.codec.v354.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v340.serializer.LegacyTelemetryEventSerializer_v340;
import org.cloudburstmc.protocol.bedrock.data.event.BellUsed;
import org.cloudburstmc.protocol.bedrock.data.event.ComposterUsed;
import org.cloudburstmc.protocol.bedrock.data.event.POIBlockInteractionType;
import org.cloudburstmc.protocol.bedrock.data.event.POICauldronUsed;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v354 extends LegacyTelemetryEventSerializer_v340 {
    public static final LegacyTelemetryEventSerializer_v354 INSTANCE = new LegacyTelemetryEventSerializer_v354();

    protected LegacyTelemetryEventSerializer_v354() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.POICAULDRON_USED, this::readPOICauldronUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.COMPOSTER_USED, this::readComposterUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.BELL_USED, this::readBellUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.POICAULDRON_USED, this::writePOICauldronUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.COMPOSTER_USED, this::writeComposterUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.BELL_USED, this::writeBellUsed);
    }

    protected POICauldronUsed readPOICauldronUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        POICauldronUsed event = new POICauldronUsed();
        event.setBlockInteractionType(POIBlockInteractionType.from(VarInts.readInt(buffer)));
        event.setItemId(VarInts.readInt(buffer));
        return event;
    }

    protected void writePOICauldronUsed(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        POICauldronUsed event = (POICauldronUsed) eventData;
        VarInts.writeInt(buffer, event.getBlockInteractionType().ordinal());
        VarInts.writeInt(buffer, event.getItemId());
    }

    protected ComposterUsed readComposterUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        ComposterUsed event = new ComposterUsed();
        event.setBlockInteractionType(POIBlockInteractionType.from(VarInts.readInt(buffer)));
        event.setItemId(VarInts.readInt(buffer));
        return event;
    }

    protected void writeComposterUsed(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        ComposterUsed event = (ComposterUsed) eventData;
        VarInts.writeInt(buffer, event.getBlockInteractionType().ordinal());
        VarInts.writeInt(buffer, event.getItemId());
    }

    protected BellUsed readBellUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        BellUsed event = new BellUsed();
        event.setItemId(VarInts.readInt(buffer));
        return event;
    }

    protected void writeBellUsed(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        BellUsed event = (BellUsed) eventData;
        VarInts.writeInt(buffer, event.getItemId());
    }
}
