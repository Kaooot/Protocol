package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.SubChunkSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkHeightmapData;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkPacketData;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkSerializer_v2168 extends SubChunkSerializer_v818 {
    public static final SubChunkSerializer_v2168 INSTANCE = new SubChunkSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        buffer.writeBoolean(packet.isCacheEnabled());
        VarInts.writeInt(buffer, packet.getDimensionType().getValue());
        this.writeCenterPos(buffer, packet.getCenterPos());
        helper.writeArray(buffer, packet.getSubChunkData(), (buf, codecHelper, data) -> this.writeSubChunkPacketData(buffer, helper, data, packet));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        packet.setCacheEnabled(buffer.readBoolean());
        packet.setDimensionType(DimensionType.from(VarInts.readInt(buffer)));
        packet.setCenterPos(this.readCenterPos(buffer));
        helper.readArray(buffer, packet.getSubChunkData(), (buf, codecHelper) -> this.readSubChunkPacketData(buffer, helper, packet));
    }

    @Override
    protected void writeSubChunkPacketData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacketData data, SubChunkPacket packet) {
        this.writeSubChunkPosOffset(buffer, helper, data.getSubChunkPosOffset());
        buffer.writeByte(data.getSubChunkRequestResult().ordinal());
        helper.writeOptionalNull(buffer, data.getSerializedSubChunk(), helper::writeByteBuf);
        this.writeSubChunkHeightmapData(buffer, helper, data.getHeightMapData());
        helper.writeOptionalNull(buffer, data.getBlobId(), ByteBuf::writeLongLE);
    }

    @Override
    protected SubChunkPacketData readSubChunkPacketData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        final SubChunkPacketData data = new SubChunkPacketData();
        data.setSubChunkPosOffset(this.readSubChunkPosOffset(buffer, helper));
        data.setSubChunkRequestResult(SubChunkRequestResult.from(buffer.readUnsignedByte()));
        data.setSerializedSubChunk(helper.readOptional(buffer, null, helper::readByteBuf));
        data.setHeightMapData(this.readSubChunkHeightmapData(buffer, helper));
        data.setBlobId(helper.readOptional(buffer, null, ByteBuf::readLongLE));
        return data;
    }

    @Override
    protected void writeSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkHeightmapData heightmapData) {
        buffer.writeByte(heightmapData.getHeightMapType().ordinal());
        helper.writeOptionalNull(buffer, heightmapData.getSubchunkHeightMap(), this::writeHeightMap);
        buffer.writeByte(heightmapData.getRenderHeightMapType().ordinal());
        helper.writeOptionalNull(buffer, heightmapData.getSubchunkRenderHeightMap(), this::writeHeightMap);
    }

    @Override
    protected SubChunkHeightmapData readSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SubChunkHeightmapData data = new SubChunkHeightmapData();
        data.setHeightMapType(HeightMapDataType.from(buffer.readUnsignedByte()));
        data.setSubchunkHeightMap(helper.readOptional(buffer, null, this::readHeightMap));
        data.setRenderHeightMapType(HeightMapDataType.from(buffer.readUnsignedByte()));
        data.setSubchunkRenderHeightMap(helper.readOptional(buffer, null, this::readHeightMap));
        return data;
    }

    private void writeCenterPos(ByteBuf buffer, Vector3i centerPos) {
        buffer.writeIntLE(centerPos.getX());
        buffer.writeIntLE(centerPos.getY());
        buffer.writeIntLE(centerPos.getZ());
    }

    protected Vector3i readCenterPos(ByteBuf buffer) {
        return Vector3i.from(buffer.readIntLE(), buffer.readIntLE(), buffer.readIntLE());
    }
}