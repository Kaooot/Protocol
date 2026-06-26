package org.cloudburstmc.protocol.bedrock.codec.v419.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.ItemStackResponseSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackNetResult;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackRequestId;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemStackResponseSerializer_v419 extends ItemStackResponseSerializer_v407 {

    public static final ItemStackResponseSerializer_v419 INSTANCE = new ItemStackResponseSerializer_v419();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
        helper.writeArray(buffer, packet.getResponses(), (buf, response) -> {
            buf.writeByte(response.getResult().ordinal());
            VarInts.writeInt(buffer, response.getClientRequestId().getID());

            if (response.getResult() != ItemStackNetResult.SUCCESS)
                return;

            helper.writeArray(buf, response.getContainers(), helper::writeItemStackResponseContainer);
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
        List<ItemStackResponseInfo> entries = packet.getResponses();
        helper.readArray(buffer, entries, buf -> {
            ItemStackNetResult result = ItemStackNetResult.from(buf.readByte());
            ItemStackRequestId requestId = new ItemStackRequestId(VarInts.readInt(buf));

            if (result != ItemStackNetResult.SUCCESS)
                return new ItemStackResponseInfo(result, requestId, Collections.emptyList());

            List<ItemStackResponseContainerInfo> containerEntries = new ArrayList<>();
            helper.readArray(buf, containerEntries, helper::readItemStackResponseContainer);
            return new ItemStackResponseInfo(result, requestId, containerEntries);
        });
    }

}
