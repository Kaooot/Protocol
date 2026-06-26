package org.cloudburstmc.protocol.bedrock.codec.v471.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkHeightmapData;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkPacketData;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkSerializer_v471 implements BedrockPacketSerializer<SubChunkPacket> {
    public static final SubChunkSerializer_v471 INSTANCE = new SubChunkSerializer_v471();

    protected static final int HEIGHT_MAP_LENGTH = 256;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        VarInts.writeInt(buffer, packet.getDimensionType().getValue());
        SubChunkPacketData subChunk = packet.getSubChunkData().get(0);
        this.writeSubChunkPacketData(buffer, helper, subChunk);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        packet.setDimensionType(DimensionType.from(VarInts.readInt(buffer)));
        SubChunkPacketData subChunk = this.readSubChunkPacketData(buffer, helper);
        packet.getSubChunkData().add(subChunk);
    }

    protected void writeSubChunkPacketData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacketData data) {
        this.writeSubChunkPosOffset(buffer, helper, data.getSubChunkPosOffset());
        VarInts.writeInt(buffer, data.getSubChunkRequestResult().ordinal());
        helper.writeOptionalNull(buffer, data.getSerializedSubChunk(), helper::writeByteBuf);
        this.writeSubChunkHeightmapData(buffer, helper, data.getHeightMapData());
    }

    protected SubChunkPacketData readSubChunkPacketData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SubChunkPacketData data = new SubChunkPacketData();
        data.setSubChunkPosOffset(this.readSubChunkPosOffset(buffer, helper));
        data.setSubChunkRequestResult(SubChunkRequestResult.from(VarInts.readInt(buffer)));
        data.setSerializedSubChunk(helper.readOptional(buffer, null, helper::readByteBuf));
        data.setHeightMapData(this.readSubChunkHeightmapData(buffer, helper));
        return data;
    }

    protected void writeSubChunkPosOffset(ByteBuf buffer, BedrockCodecHelper helper, Vector3i subChunkPosOffset) {
        helper.writeVector3i(buffer, subChunkPosOffset);
    }

    protected Vector3i readSubChunkPosOffset(ByteBuf buffer, BedrockCodecHelper helper) {
        return helper.readVector3i(buffer);
    }

    protected void writeSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkHeightmapData heightmapData) {
        buffer.writeByte(heightmapData.getHeightMapType().ordinal());
        this.writeHeightMap(buffer, heightmapData.getSubchunkHeightMap());
    }

    protected SubChunkHeightmapData readSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SubChunkHeightmapData data = new SubChunkHeightmapData();
        data.setHeightMapType(HeightMapDataType.from(buffer.readByte()));
        data.setSubchunkHeightMap(this.readHeightMap(buffer));
        return data;
    }

    protected void writeHeightMap(ByteBuf buffer, ByteBuf heightMap) {
        buffer.writeBytes(heightMap);
    }

    protected ByteBuf readHeightMap(ByteBuf buffer) {
        return buffer.readRetainedSlice(HEIGHT_MAP_LENGTH);
    }
}