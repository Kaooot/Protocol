package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v649.serializer.LevelChunkSerializer_v649;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LevelChunkSerializer_v2168 extends LevelChunkSerializer_v649 {
    public static final LevelChunkSerializer_v2168 INSTANCE = new LevelChunkSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
        VarInts.writeInt(buffer, packet.getChunkX());
        VarInts.writeInt(buffer, packet.getChunkZ());
        VarInts.writeInt(buffer, packet.getDimension().getValue());
        VarInts.writeUnsignedInt(buffer, packet.getSubChunksCount());
        helper.writeOptional(buffer, value -> packet.isClientNeedsToRequestSubChunks(), packet.getClientRequestSubChunkLimit(), VarInts::writeInt);
        buffer.writeBoolean(packet.isCacheEnabled());
        helper.writeArray(buffer, packet.getCacheBlobs(), ByteBuf::writeLongLE);
        helper.writeByteBuf(buffer, packet.getSerializedChunkData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
        packet.setChunkX(VarInts.readInt(buffer));
        packet.setChunkZ(VarInts.readInt(buffer));
        packet.setDimension(DimensionType.from(VarInts.readInt(buffer)));
        packet.setSubChunksCount(VarInts.readUnsignedInt(buffer));
        packet.setClientRequestSubChunkLimit(helper.readOptional(buffer, null, (buf, codecHelper) -> {
            packet.setClientNeedsToRequestSubChunks(true);
            return VarInts.readInt(buf);
        }));
        packet.setCacheEnabled(buffer.readBoolean());
        helper.readArray(buffer, packet.getCacheBlobs(), ByteBuf::readLongLE, MAX_BLOBS);
        packet.setSerializedChunkData(helper.readByteBuf(buffer));
    }
}