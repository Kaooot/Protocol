package org.cloudburstmc.protocol.bedrock.codec.v990.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v486.serializer.SubChunkRequestSerializer_v486;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkRequestSerializer_v990 extends SubChunkRequestSerializer_v486 {
    public static final SubChunkRequestSerializer_v990 INSTANCE = new SubChunkRequestSerializer_v990();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
        VarInts.writeInt(buffer, packet.getDimensionType());
        helper.writeArray(buffer, packet.getSubChunkPosOffsetList(), this::writeSubChunkOffset);
        buffer.writeIntLE(packet.getCenterPos().getX());
        buffer.writeIntLE(packet.getCenterPos().getY());
        buffer.writeIntLE(packet.getCenterPos().getZ());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
        packet.setDimensionType(VarInts.readInt(buffer));
        helper.readArray(buffer, packet.getSubChunkPosOffsetList(), this::readSubChunkOffset, MAX_SUB_CHUNKS);
        final int x = buffer.readIntLE();
        final int y = buffer.readIntLE();
        final int z = buffer.readIntLE();
        packet.setCenterPos(Vector3i.from(x, y, z));
    }
}