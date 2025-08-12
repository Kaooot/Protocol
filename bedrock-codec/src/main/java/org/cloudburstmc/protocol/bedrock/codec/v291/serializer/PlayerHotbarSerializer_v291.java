package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PlayerHotbarPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerHotbarSerializer_v291 implements BedrockPacketSerializer<PlayerHotbarPacket> {
    public static final PlayerHotbarSerializer_v291 INSTANCE = new PlayerHotbarSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerHotbarPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getSelectedSlot());
        buffer.writeByte(packet.getContainerID());
        buffer.writeBoolean(packet.isShouldSelectSlot());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerHotbarPacket packet) {
        packet.setSelectedSlot(VarInts.readUnsignedInt(buffer));
        packet.setContainerID(buffer.readUnsignedByte());
        packet.setShouldSelectSlot(buffer.readBoolean());
    }
}
