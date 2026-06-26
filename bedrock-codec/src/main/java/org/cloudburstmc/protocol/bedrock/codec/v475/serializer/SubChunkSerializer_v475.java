package org.cloudburstmc.protocol.bedrock.codec.v475.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v471.serializer.SubChunkSerializer_v471;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkHeightmapData;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkSerializer_v475 extends SubChunkSerializer_v471 {
    public static final SubChunkSerializer_v475 INSTANCE = new SubChunkSerializer_v475();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeBoolean(packet.isCacheEnabled());
        if (packet.isCacheEnabled()) {
            buffer.writeLongLE(packet.getSubChunkData().get(0).getBlobId());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setCacheEnabled(buffer.readBoolean());
        if (packet.isCacheEnabled()) {
            packet.getSubChunkData().get(0).setBlobId(buffer.readLongLE());
        }
    }

    @Override
    protected void writeSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkHeightmapData heightmapData) {
        buffer.writeByte(heightmapData.getHeightMapType().ordinal());
        if (heightmapData.getHeightMapType().equals(HeightMapDataType.HAS_DATA)) {
            this.writeHeightMap(buffer, heightmapData.getSubchunkHeightMap());
        }
    }

    @Override
    protected SubChunkHeightmapData readSubChunkHeightmapData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SubChunkHeightmapData data = new SubChunkHeightmapData();
        data.setHeightMapType(HeightMapDataType.from(buffer.readByte()));
        if (data.getHeightMapType().equals(HeightMapDataType.HAS_DATA)) {
            data.setSubchunkHeightMap(this.readHeightMap(buffer));
        }
        return data;
    }
}