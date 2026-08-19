package org.cloudburstmc.protocol.bedrock.codec.v486.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v471.serializer.SubChunkRequestSerializer_v471;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkRequestSerializer_v486 extends SubChunkRequestSerializer_v471 {
    // A limit of how many sub chunks can client request within a single packet
    // It seems that client does not have any cap on how many sub chunks it can request,
    // and in some edge cases it requests all sub chunks within the view distance
    // The limit set here is based on maximum view distance vanilla client supports (96 chunks)
    protected static final int MAX_SUB_CHUNKS = 8129; // circle area * 96 chunks * 24 sub chunks per chunk

    public static final SubChunkRequestSerializer_v486 INSTANCE = new SubChunkRequestSerializer_v486();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
        VarInts.writeInt(buffer, packet.getDimensionType().hashCode());
        helper.writeVector3i(buffer, packet.getCenterPos());
        helper.writeArray(buffer, packet.getSubChunkPosOffsetList(), ByteBuf::writeIntLE, this::writeSubChunkOffset);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
        packet.setDimensionType(DimensionType.from(VarInts.readInt(buffer)));
        packet.setCenterPos(helper.readVector3i(buffer));
        helper.readArray(buffer, packet.getSubChunkPosOffsetList(), ByteBuf::readIntLE, this::readSubChunkOffset, MAX_SUB_CHUNKS);
    }

    protected void writeSubChunkOffset(ByteBuf buffer, Vector3i offsetPosition) {
        buffer.writeByte(offsetPosition.getX());
        buffer.writeByte(offsetPosition.getY());
        buffer.writeByte(offsetPosition.getZ());
    }

    protected Vector3i readSubChunkOffset(ByteBuf buffer) {
        return Vector3i.from(buffer.readByte(), buffer.readByte(), buffer.readByte());
    }
}
