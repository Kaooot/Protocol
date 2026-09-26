package org.cloudburstmc.protocol.bedrock.codec.v630.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryLayout;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryLeftTabIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryOptions;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryRightTabIndex;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerInventoryOptionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class SetPlayerInventoryOptionsSerializer_v630 implements BedrockPacketSerializer<SetPlayerInventoryOptionsPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerInventoryOptionsPacket packet) {
        InventoryOptions options = packet.getInventoryOptions();
        VarInts.writeInt(buffer, options.getLeftInventoryTab().ordinal());
        VarInts.writeInt(buffer, options.getRightInventoryTab().ordinal());
        buffer.writeBoolean(options.isFiltering());
        VarInts.writeInt(buffer, options.getLayoutInv().ordinal());
        VarInts.writeInt(buffer, options.getLayoutCraft().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerInventoryOptionsPacket packet) {
        InventoryOptions options = new InventoryOptions();
        options.setLeftInventoryTab(InventoryLeftTabIndex.from(VarInts.readInt(buffer)));
        options.setRightInventoryTab(InventoryRightTabIndex.from(VarInts.readInt(buffer)));
        options.setFiltering(buffer.readBoolean());
        options.setLayoutInv(InventoryLayout.from(VarInts.readInt(buffer)));
        options.setLayoutCraft(InventoryLayout.from(VarInts.readInt(buffer)));
        packet.setInventoryOptions(options);
    }
}
