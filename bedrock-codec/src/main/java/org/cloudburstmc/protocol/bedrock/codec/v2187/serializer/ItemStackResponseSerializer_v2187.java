package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.ItemStackResponseSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackNetResult;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainerInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackRequestId;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemStackResponseSerializer_v2187 extends ItemStackResponseSerializer_v2168 {
    public static final ItemStackResponseSerializer_v2187 INSTANCE = new ItemStackResponseSerializer_v2187();

    @Override
    protected void writeItemStackResponseInfo(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponseInfo info) {
        buffer.writeByte(info.getResult().ordinal());
        VarInts.writeInt(buffer, info.getClientRequestId().getID());
        helper.writeOptionalNull(
                buffer,
                info.getContainers(),
                (buf, codecHelper, containers) ->
                        codecHelper.writeArray(buf, containers, codecHelper::writeItemStackResponseContainer)
        );
    }

    @Override
    protected ItemStackResponseInfo readItemStackResponseInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemStackNetResult result = ItemStackNetResult.from(buffer.readUnsignedByte());
        final ItemStackRequestId clientRequestId = new ItemStackRequestId(VarInts.readInt(buffer));
        final List<ItemStackResponseContainerInfo> containers = helper.readOptional(buffer,
                new ObjectArrayList<>(),
                (buf, codecHelper) -> {
                    final List<ItemStackResponseContainerInfo> containerInfos = new ObjectArrayList<>();
                    codecHelper.readArray(buf, containerInfos, codecHelper::readItemStackResponseContainer);
                    return containerInfos;
                }
        );
        return new ItemStackResponseInfo(result, clientRequestId, containers);
    }
}