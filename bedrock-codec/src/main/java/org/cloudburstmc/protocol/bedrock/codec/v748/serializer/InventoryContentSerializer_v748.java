package org.cloudburstmc.protocol.bedrock.codec.v748.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryContentSerializer_v748 implements BedrockPacketSerializer<InventoryContentPacket> {
    public static final InventoryContentSerializer_v748 INSTANCE = new InventoryContentSerializer_v748();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getInventoryId());
        helper.writeArray(buffer, packet.getSlots(), helper::writeNetItem);
        helper.writeFullContainerName(buffer, packet.getFullContainerName());
        helper.writeNetItem(buffer, packet.getStorageItem());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
        packet.setInventoryId(VarInts.readUnsignedInt(buffer));
        helper.readArray(buffer, packet.getSlots(), helper::readNetItem);
        packet.setFullContainerName(helper.readFullContainerName(buffer));
        packet.setStorageItem(helper.readNetItem(buffer));
    }
}