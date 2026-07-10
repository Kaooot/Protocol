package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v486.serializer.SubChunkSerializer_v486;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkHeightmapData;

public class SubChunkSerializer_v818 extends SubChunkSerializer_v486 {

    public static final SubChunkSerializer_v818 INSTANCE = new SubChunkSerializer_v818();

    @Override
    protected void writeSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkHeightmapData heightmapData) {
        buffer.writeByte(heightmapData.getHeightMapType().ordinal());
        if (heightmapData.getHeightMapType().equals(HeightMapDataType.HAS_DATA)) {
            this.writeHeightMap(buffer, heightmapData.getSubchunkHeightMap());
        }
        buffer.writeByte(heightmapData.getRenderHeightMapType().ordinal());
        if (heightmapData.getRenderHeightMapType().equals(HeightMapDataType.HAS_DATA)) {
            this.writeHeightMap(buffer, heightmapData.getSubchunkRenderHeightMap());
        }
    }

    @Override
    protected SubChunkHeightmapData readSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SubChunkHeightmapData data = new SubChunkHeightmapData();
        data.setHeightMapType(HeightMapDataType.from(buffer.readByte()));
        if (data.getHeightMapType().equals(HeightMapDataType.HAS_DATA)) {
            data.setSubchunkHeightMap(this.readHeightMap(buffer));
        }
        data.setRenderHeightMapType(HeightMapDataType.from(buffer.readByte()));
        if (data.getRenderHeightMapType().equals(HeightMapDataType.HAS_DATA)) {
            data.setSubchunkRenderHeightMap(this.readHeightMap(buffer));
        }
        return data;
    }
}
