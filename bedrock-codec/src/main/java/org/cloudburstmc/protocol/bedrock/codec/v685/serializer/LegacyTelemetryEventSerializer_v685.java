package org.cloudburstmc.protocol.bedrock.codec.v685.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v671.serializer.LegacyTelemetryEventSerializer_v671;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.data.event.ItemUsedEventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

public class LegacyTelemetryEventSerializer_v685 extends LegacyTelemetryEventSerializer_v671 {
    public static final LegacyTelemetryEventSerializer_v685 INSTANCE = new LegacyTelemetryEventSerializer_v685();

    public LegacyTelemetryEventSerializer_v685() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.ITEM_USED, this::readItemUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.ITEM_USED, this::writeItemUsed);
    }

    protected ItemUsedEventData readItemUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        short itemId = buffer.readShortLE();
        int itemAux = buffer.readIntLE();
        int useMethod = buffer.readIntLE();
        int useCount = buffer.readIntLE();
        return new ItemUsedEventData(itemId, itemAux, useMethod, useCount);
    }

    protected void writeItemUsed(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        ItemUsedEventData event = (ItemUsedEventData) eventData;
        buffer.writeShortLE(event.getItemId());
        buffer.writeIntLE(event.getItemAux());
        buffer.writeIntLE(event.getUseMethod());
        buffer.writeIntLE(event.getCount());
    }
}