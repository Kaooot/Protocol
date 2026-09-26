package org.cloudburstmc.protocol.bedrock.codec.v685.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v671.serializer.LegacyTelemetryEventSerializer_v671;
import org.cloudburstmc.protocol.bedrock.data.event.ItemUsed;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;

public class LegacyTelemetryEventSerializer_v685 extends LegacyTelemetryEventSerializer_v671 {
    public static final LegacyTelemetryEventSerializer_v685 INSTANCE = new LegacyTelemetryEventSerializer_v685();

    protected LegacyTelemetryEventSerializer_v685() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.ITEM_USED, this::readItemUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.ITEM_USED, this::writeItemUsed);
    }

    protected ItemUsed readItemUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        ItemUsed event = new ItemUsed();
        event.setItemId(buffer.readShortLE());
        event.setItemAux(buffer.readIntLE());
        event.setUseMethod(buffer.readIntLE());
        event.setCount(buffer.readIntLE());
        return event;
    }

    protected void writeItemUsed(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        ItemUsed event = (ItemUsed) eventData;
        buffer.writeShortLE(event.getItemId());
        buffer.writeIntLE(event.getItemAux());
        buffer.writeIntLE(event.getUseMethod());
        buffer.writeIntLE(event.getCount());
    }
}
