package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.BlockPickRequestPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockPickRequestSerializer_v291 implements BedrockPacketSerializer<BlockPickRequestPacket> {
    public static final BlockPickRequestSerializer_v291 INSTANCE = new BlockPickRequestSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BlockPickRequestPacket packet) {
        helper.writeVector3i(buffer, packet.getPosition());
        buffer.writeBoolean(packet.isWithData());
        buffer.writeByte(packet.getMaxSlots());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BlockPickRequestPacket packet) {
        packet.setPosition(helper.readVector3i(buffer));
        packet.setWithData(buffer.readBoolean());
        packet.setMaxSlots(buffer.readUnsignedByte());
    }
}
