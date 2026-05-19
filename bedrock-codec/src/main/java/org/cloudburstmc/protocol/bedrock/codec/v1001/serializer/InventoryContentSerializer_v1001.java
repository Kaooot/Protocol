package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v748.serializer.InventoryContentSerializer_v748;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryContentSerializer_v1001 extends InventoryContentSerializer_v748 {
    public static final InventoryContentSerializer_v1001 INSTANCE = new InventoryContentSerializer_v1001();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getContainerId());
        helper.writeArray(buffer, packet.getSlots(), helper::writeNetworkItemStackDescriptor);
        helper.writeFullContainerName(buffer, packet.getFullContainerName());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getStorageItem());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
        packet.setContainerId(VarInts.readUnsignedInt(buffer));
        helper.readArray(buffer, packet.getSlots(), helper::readNetworkItemStackDescriptor);
        packet.setFullContainerName(helper.readFullContainerName(buffer));
        packet.setStorageItem(helper.readNetworkItemStackDescriptor(buffer));
    }
}