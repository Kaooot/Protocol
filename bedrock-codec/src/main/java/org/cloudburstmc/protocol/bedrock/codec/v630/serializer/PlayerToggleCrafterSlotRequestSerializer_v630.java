package org.cloudburstmc.protocol.bedrock.codec.v630.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PlayerToggleCrafterSlotRequestPacket;

public class PlayerToggleCrafterSlotRequestSerializer_v630 implements BedrockPacketSerializer<PlayerToggleCrafterSlotRequestPacket> {

    public static final PlayerToggleCrafterSlotRequestSerializer_v630 INSTANCE = new PlayerToggleCrafterSlotRequestSerializer_v630();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerToggleCrafterSlotRequestPacket packet) {
        buffer.writeIntLE(packet.getPosX());
        buffer.writeIntLE(packet.getPosY());
        buffer.writeIntLE(packet.getPosZ());
        buffer.writeByte(packet.getSlotIndex());
        buffer.writeBoolean(packet.isDisabled());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerToggleCrafterSlotRequestPacket packet) {
        packet.setPosX(buffer.readIntLE());
        packet.setPosY(buffer.readIntLE());
        packet.setPosZ(buffer.readIntLE());
        packet.setSlotIndex(buffer.readByte());
        packet.setDisabled(buffer.readBoolean());
    }
}
