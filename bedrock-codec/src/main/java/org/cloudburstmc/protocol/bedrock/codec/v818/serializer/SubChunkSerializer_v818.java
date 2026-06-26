package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v486.serializer.SubChunkSerializer_v486;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkPacketData;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;

public class SubChunkSerializer_v818 extends SubChunkSerializer_v486 {

    public static final SubChunkSerializer_v818 INSTANCE = new SubChunkSerializer_v818();

    /*@Override
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
        buffer.writeByte(subChunk.getHeightMapData().getRenderHeightMapType().ordinal());
        if (subChunk.getHeightMapData().getRenderHeightMapType() == HeightMapDataType.HAS_DATA) {
            ByteBuf renderHeightMapBuf = subChunk.getRenderHeightMapData();
            buffer.writeBytes(renderHeightMapBuf, renderHeightMapBuf.readerIndex(), HEIGHT_MAP_LENGTH);
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
        subChunk.setRenderHeightMapDataType(HeightMapDataType.values()[buffer.readByte()]);
        if (subChunk.getRenderHeightMapDataType() == HeightMapDataType.HAS_DATA) {
            subChunk.setRenderHeightMapData(buffer.readRetainedSlice(HEIGHT_MAP_LENGTH));
        }
        if (packet.isCacheEnabled()) {
            subChunk.setBlobId(buffer.readLongLE());
        }
        return subChunk;
    }*/
}
