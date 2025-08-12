package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemStackResponseSerializer_v407 implements BedrockPacketSerializer<ItemStackResponsePacket> {

    public static final ItemStackResponseSerializer_v407 INSTANCE = new ItemStackResponseSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
        helper.writeArray(buffer, packet.getResponses(), (buf, response) -> {
            buf.writeBoolean(response.isSuccess());
            VarInts.writeInt(buffer, response.getRequestId());

            if (!response.isSuccess())
                return;

            helper.writeArray(buf, response.getContainerInfo(), (buf2, containerEntry) -> {
                helper.writeContainerSlotType(buf2, containerEntry.getContainerEnumName());
                helper.writeArray(buf2, containerEntry.getSlots(), this::writeItemEntry);
            });
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
        List<ItemStackResponseInfo> entries = packet.getResponses();
        helper.readArray(buffer, entries, buf -> {
            boolean success = buf.readBoolean();
            int requestId = VarInts.readInt(buf);

            if (!success)
                return new ItemStackResponseInfo(success, requestId, Collections.emptyList());

            List<ItemStackResponseContainerInfo> containerEntries = new ArrayList<>();
            helper.readArray(buf, containerEntries, buf2 -> {
                ContainerEnumName container = helper.readContainerSlotType(buf2);

                List<ItemStackResponseSlotInfo> itemEntries = new ArrayList<>();
                helper.readArray(buf2, itemEntries, byteBuf -> this.readItemEntry(byteBuf, helper));
                return new ItemStackResponseContainerInfo(container, itemEntries, null);
            });
            return new ItemStackResponseInfo(success, requestId, containerEntries);
        });
    }

    protected ItemStackResponseSlotInfo readItemEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        return new ItemStackResponseSlotInfo(
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                VarInts.readInt(buffer),
                "",
                0,
                "");
    }

    protected void writeItemEntry(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponseSlotInfo itemEntry) {
        buffer.writeByte(itemEntry.getRequestedSlot());
        buffer.writeByte(itemEntry.getSlot());
        buffer.writeByte(itemEntry.getAmount());
        VarInts.writeInt(buffer, itemEntry.getItemStackNetId());
    }
}
