package org.cloudburstmc.protocol.bedrock.codec.v630.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryLayout;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryLeftTabIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryRightTabIndex;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerInventoryOptionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class SetPlayerInventoryOptionsSerializer_v630 implements BedrockPacketSerializer<SetPlayerInventoryOptionsPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerInventoryOptionsPacket packet) {
        VarInts.writeInt(buffer, packet.getLeftInventoryTab().ordinal());
        VarInts.writeInt(buffer, packet.getRightInventoryTab().ordinal());
        buffer.writeBoolean(packet.isFiltering());
        VarInts.writeInt(buffer, packet.getLayoutInv().ordinal());
        VarInts.writeInt(buffer, packet.getLayoutCraft().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerInventoryOptionsPacket packet) {
        packet.setLeftInventoryTab(InventoryLeftTabIndex.VALUES[VarInts.readInt(buffer)]);
        packet.setRightInventoryTab(InventoryRightTabIndex.VALUES[VarInts.readInt(buffer)]);
        packet.setFiltering(buffer.readBoolean());
        packet.setLayoutInv(InventoryLayout.VALUES[VarInts.readInt(buffer)]);
        packet.setLayoutCraft(InventoryLayout.VALUES[VarInts.readInt(buffer)]);
    }
}