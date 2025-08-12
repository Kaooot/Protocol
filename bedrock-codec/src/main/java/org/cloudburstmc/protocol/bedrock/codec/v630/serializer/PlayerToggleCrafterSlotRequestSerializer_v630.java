package org.cloudburstmc.protocol.bedrock.codec.v630.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PlayerToggleCrafterSlotRequestPacket;

public class PlayerToggleCrafterSlotRequestSerializer_v630 implements BedrockPacketSerializer<PlayerToggleCrafterSlotRequestPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerToggleCrafterSlotRequestPacket packet) {
        buffer.writeIntLE(packet.getPos().getX());
        buffer.writeIntLE(packet.getPos().getY());
        buffer.writeIntLE(packet.getPos().getZ());
        buffer.writeByte(packet.getSlotIndex());
        buffer.writeBoolean(packet.isDisabled());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerToggleCrafterSlotRequestPacket packet) {
        int x = buffer.readIntLE();
        int y = buffer.readIntLE();
        int z = buffer.readIntLE();
        packet.setPos(Vector3i.from(x, y, z));
        packet.setSlotIndex(buffer.readByte());
        packet.setDisabled(buffer.readBoolean());
    }
}
