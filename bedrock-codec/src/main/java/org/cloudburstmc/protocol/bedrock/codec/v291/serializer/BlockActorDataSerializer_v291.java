package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.BlockActorDataPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockActorDataSerializer_v291 implements BedrockPacketSerializer<BlockActorDataPacket> {
    public static final BlockActorDataSerializer_v291 INSTANCE = new BlockActorDataSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BlockActorDataPacket packet) {
        helper.writeBlockPosition(buffer, packet.getBlockPosition());
        helper.writeTag(buffer, packet.getActorDataTags());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BlockActorDataPacket packet) {
        packet.setBlockPosition(helper.readBlockPosition(buffer));
        packet.setActorDataTags(helper.readTag(buffer, NbtMap.class));
    }
}
