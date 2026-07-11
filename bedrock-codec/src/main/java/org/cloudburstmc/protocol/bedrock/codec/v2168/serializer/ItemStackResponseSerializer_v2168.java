package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v419.serializer.ItemStackResponseSerializer_v419;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackNetResult;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackRequestId;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemStackResponseSerializer_v2168 extends ItemStackResponseSerializer_v419 {
    public static final ItemStackResponseSerializer_v2168 INSTANCE = new ItemStackResponseSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
        helper.writeArray(buffer, packet.getResponses(), this::writeItemStackResponseInfo);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
        helper.readArray(buffer, packet.getResponses(), this::readItemStackResponseInfo);
    }

    protected void writeItemStackResponseInfo(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponseInfo info) {
        buffer.writeByte(info.getResult().ordinal());
        VarInts.writeInt(buffer, info.getClientRequestId().getID());
        final boolean containersHasValue = !info.getContainers().isEmpty();
        buffer.writeBoolean(containersHasValue);
        if (containersHasValue) {
            buffer.writeBoolean(true);
            helper.writeArray(buffer, info.getContainers(), helper::writeItemStackResponseContainer);
        }
    }

    protected ItemStackResponseInfo readItemStackResponseInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemStackNetResult result = ItemStackNetResult.from(buffer.readUnsignedByte());
        final ItemStackRequestId clientRequestId = new ItemStackRequestId(VarInts.readInt(buffer));
        final List<ItemStackResponseContainerInfo> containers = new ObjectArrayList<>();
        final boolean containersHasValue = buffer.readBoolean();
        if (containersHasValue) {
            if (buffer.readBoolean()) {
                helper.readArray(buffer, containers, helper::readItemStackResponseContainer);
            }
        }
        return new ItemStackResponseInfo(result, clientRequestId, containers);
    }
}