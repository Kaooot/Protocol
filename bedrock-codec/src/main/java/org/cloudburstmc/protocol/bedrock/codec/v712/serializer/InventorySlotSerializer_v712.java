package org.cloudburstmc.protocol.bedrock.codec.v712.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class InventorySlotSerializer_v712 implements BedrockPacketSerializer<InventorySlotPacket> {
    public static final InventorySlotSerializer_v712 INSTANCE = new InventorySlotSerializer_v712();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getContainerID());
        VarInts.writeUnsignedInt(buffer, packet.getSlot());
        VarInts.writeUnsignedInt(buffer, packet.getFullContainerName() == null || packet.getFullContainerName().getDynamicID() == null ? 0 : packet.getFullContainerName().getDynamicID());
        helper.writeNetItem(buffer, packet.getItem());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
        packet.setContainerID(VarInts.readUnsignedInt(buffer));
        packet.setSlot(VarInts.readUnsignedInt(buffer));

        FullContainerName containerName = new FullContainerName(ContainerEnumName.UNKNOWN,
                VarInts.readUnsignedInt(buffer));
        packet.setFullContainerName(containerName);

        packet.setItem(helper.readNetItem(buffer));
    }
}