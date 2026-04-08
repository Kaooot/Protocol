package org.cloudburstmc.protocol.bedrock.codec.v974.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v748.serializer.InventorySlotSerializer_v748;
import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class InventorySlotSerializer_v974 extends InventorySlotSerializer_v748 {
    public static final InventorySlotSerializer_v748 INSTANCE = new InventorySlotSerializer_v974();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getContainerID());
        VarInts.writeUnsignedInt(buffer, packet.getSlot());
        helper.writeOptionalNull(buffer, packet.getFullContainerName(), helper::writeFullContainerName);
        helper.writeOptionalNull(buffer, packet.getStorageItem(), helper::writeNetworkItemStackDescriptor);
        helper.writeNetworkItemStackDescriptor(buffer, packet.getItem());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
        packet.setContainerID(VarInts.readUnsignedInt(buffer));
        packet.setSlot(VarInts.readUnsignedInt(buffer));
        packet.setFullContainerName(helper.readOptional(buffer, null, helper::readFullContainerName));
        packet.setStorageItem(helper.readOptional(buffer, null, helper::readNetworkItemStackDescriptor));
        packet.setItem(helper.readNetworkItemStackDescriptor(buffer));
    }
}