package org.cloudburstmc.protocol.bedrock.codec.v486.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v475.serializer.SubChunkSerializer_v475;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkPacketData;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkSerializer_v486 extends SubChunkSerializer_v475 {

    public static final SubChunkSerializer_v486 INSTANCE = new SubChunkSerializer_v486();

    /*@Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        buffer.writeBoolean(packet.isCacheEnabled());
        VarInts.writeInt(buffer, packet.getDimensionType().getValue());
        helper.writeVector3i(buffer, packet.getCenterPos());

        buffer.writeIntLE(packet.getSubChunkData().size());
        packet.getSubChunkData().forEach(subChunk -> this.serializeSubChunk(buffer, helper, packet, subChunk));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        packet.setCacheEnabled(buffer.readBoolean());
        packet.setDimensionType(DimensionType.from(VarInts.readInt(buffer)));
        packet.setCenterPos(helper.readVector3i(buffer));

        int size = buffer.readIntLE(); // Unsigned but realistically, we're not going to read that many.
        for (int i = 0; i < size; i++) {
            packet.getSubChunkData().add(this.deserializeSubChunk(buffer, helper, packet));
        }
    }

    @Override
    protected void serializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet, SubChunkPacketData subChunk) {
        this.writeSubChunkOffset(buffer, subChunk.getSubChunkPosOffset());
        buffer.writeByte(subChunk.getSubChunkRequestResult().ordinal());
        if (subChunk.getSubChunkRequestResult() != SubChunkRequestResult.SUCCESS_ALL_AIR || !packet.isCacheEnabled()) {
            helper.writeByteBuf(buffer, subChunk.getSerializedSubChunk());
        }
        buffer.writeByte(subChunk.getHeightMapDataType().ordinal());
        if (subChunk.getHeightMapDataType() == HeightMapDataType.HAS_DATA) {
            ByteBuf heightMapBuf = subChunk.getHeightMapData();
            buffer.writeBytes(heightMapBuf, heightMapBuf.readerIndex(), HEIGHT_MAP_LENGTH);
        }
        if (packet.isCacheEnabled()) {
            buffer.writeLongLE(subChunk.getBlobId());
        }
    }

    @Override
    protected SubChunkPacketData deserializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        SubChunkPacketData subChunk = new SubChunkPacketData();
        subChunk.setSubChunkPosOffset(this.readSubChunkOffset(buffer));
        subChunk.setSubChunkRequestResult(SubChunkRequestResult.values()[buffer.readByte()]);
        if (subChunk.getSubChunkRequestResult() != SubChunkRequestResult.SUCCESS_ALL_AIR || !packet.isCacheEnabled()) {
            subChunk.setSerializedSubChunk(helper.readByteBuf(buffer));
        }
        subChunk.setHeightMapDataType(HeightMapDataType.values()[buffer.readByte()]);
        if (subChunk.getHeightMapDataType() == HeightMapDataType.HAS_DATA) {
            subChunk.setHeightMapData(buffer.readRetainedSlice(HEIGHT_MAP_LENGTH));
        }
        if (packet.isCacheEnabled()) {
            subChunk.setBlobId(buffer.readLongLE());
        }
        return subChunk;
    }*/

    @Override
    protected void writeSubChunkPosOffset(ByteBuf buffer, BedrockCodecHelper helper, Vector3i subChunkPosOffset) {
        buffer.writeByte(subChunkPosOffset.getX());
        buffer.writeByte(subChunkPosOffset.getY());
        buffer.writeByte(subChunkPosOffset.getZ());
    }

    @Override
    protected Vector3i readSubChunkPosOffset(ByteBuf buffer, BedrockCodecHelper helper) {
        return Vector3i.from(buffer.readByte(), buffer.readByte(), buffer.readByte());
    }
}