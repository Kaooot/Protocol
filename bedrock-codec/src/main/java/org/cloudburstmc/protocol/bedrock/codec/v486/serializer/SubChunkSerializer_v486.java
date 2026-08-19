package org.cloudburstmc.protocol.bedrock.codec.v486.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v475.serializer.SubChunkSerializer_v475;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkPacketData;
import org.cloudburstmc.protocol.bedrock.data.payload.chunk.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubChunkSerializer_v486 extends SubChunkSerializer_v475 {

    protected static final int MAX_SUB_CHUNKS = 8192;

    public static final SubChunkSerializer_v486 INSTANCE = new SubChunkSerializer_v486();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        buffer.writeBoolean(packet.isCacheEnabled());
        VarInts.writeInt(buffer, packet.getDimensionType().getValue());
        helper.writeVector3i(buffer, packet.getCenterPos());

        buffer.writeIntLE(packet.getSubChunkData().size());
        packet.getSubChunkData().forEach(subChunk -> this.writeSubChunkPacketData(buffer, helper, subChunk, packet));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        packet.setCacheEnabled(buffer.readBoolean());
        packet.setDimensionType(DimensionType.from(VarInts.readInt(buffer)));
        packet.setCenterPos(helper.readVector3i(buffer));

        int size = buffer.readIntLE();
        checkArgument(size <= MAX_SUB_CHUNKS, "Tried to read %s Sub Chunks but maximum is %s", MAX_SUB_CHUNKS);
        for (int i = 0; i < size; i++) {
            packet.getSubChunkData().add(this.readSubChunkPacketData(buffer, helper, packet));
        }
    }

    @Override
    protected void writeSubChunkPacketData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacketData data, SubChunkPacket packet) {
        this.writeSubChunkPosOffset(buffer, helper, data.getSubChunkPosOffset());
        buffer.writeByte(data.getSubChunkRequestResult().ordinal());
        if (!data.getSubChunkRequestResult().equals(SubChunkRequestResult.SUCCESS_ALL_AIR) || !packet.isCacheEnabled()) {
            helper.writeByteBuf(buffer, data.getSerializedSubChunk());
        }
        this.writeSubChunkHeightmapData(buffer, helper, data.getHeightMapData());
        if (packet.isCacheEnabled()) {
            buffer.writeLongLE(data.getBlobId());
        }
    }

    @Override
    protected SubChunkPacketData readSubChunkPacketData(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
        final SubChunkPacketData data = new SubChunkPacketData();
        data.setSubChunkPosOffset(this.readSubChunkPosOffset(buffer, helper));
        data.setSubChunkRequestResult(SubChunkRequestResult.from(buffer.readByte()));
        if (!data.getSubChunkRequestResult().equals(SubChunkRequestResult.SUCCESS_ALL_AIR) || !packet.isCacheEnabled()) {
            data.setSerializedSubChunk(helper.readByteBuf(buffer));
        }
        data.setHeightMapData(this.readSubChunkHeightmapData(buffer, helper));
        if (packet.isCacheEnabled()) {
            data.setBlobId(buffer.readLongLE());
        }
        return data;
    }

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