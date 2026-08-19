package org.cloudburstmc.protocol.bedrock.codec.v486.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.LevelChunkSerializer_v361;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LevelChunkSerializer_v486 extends LevelChunkSerializer_v361 {
    public static final LevelChunkSerializer_v486 INSTANCE = new LevelChunkSerializer_v486();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
        this.writeChunkLocation(buffer, packet);

        if (!packet.isClientNeedsToRequestSubChunks()) {
            VarInts.writeUnsignedInt(buffer, packet.getSubChunksCount());
        } else if (packet.getClientRequestSubChunkLimit() < 0) {
            VarInts.writeUnsignedInt(buffer, -1);
        } else {
            VarInts.writeUnsignedInt(buffer, -2);
            buffer.writeShortLE(packet.getClientRequestSubChunkLimit());
        }

        buffer.writeBoolean(packet.isCacheEnabled());
        if (packet.isCacheEnabled()) {
            LongList blobIds = packet.getCacheBlobs();
            VarInts.writeUnsignedInt(buffer, blobIds.size());

            for (long blobId : blobIds) {
                buffer.writeLongLE(blobId);
            }
        }

        helper.writeByteBuf(buffer, packet.getSerializedChunkData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
        this.readChunkLocation(buffer, packet);

        int subChunksCount = VarInts.readUnsignedInt(buffer);
        if (subChunksCount >= 0) {
            packet.setSubChunksCount(subChunksCount);
        } else {
            packet.setClientNeedsToRequestSubChunks(true);
            if (subChunksCount == -1) {
                packet.setClientRequestSubChunkLimit(subChunksCount);
            } else if (subChunksCount == -2) {
                packet.setClientRequestSubChunkLimit(buffer.readUnsignedShortLE());
            }
        }

        packet.setCacheEnabled(buffer.readBoolean());

        if (packet.isCacheEnabled()) {
            LongList blobIds = packet.getCacheBlobs();
            int length = VarInts.readUnsignedInt(buffer);
            checkArgument(length <= MAX_BLOBS, "Tried to read %s Blob IDs but maximum is %s", length, MAX_BLOBS);

            for (int i = 0; i < length; i++) {
                blobIds.add(buffer.readLongLE());
            }
        }
        packet.setSerializedChunkData(helper.readByteBuf(buffer));
    }

    protected void writeChunkLocation(ByteBuf buffer, LevelChunkPacket packet) {
        VarInts.writeInt(buffer, packet.getChunkX());
        VarInts.writeInt(buffer, packet.getChunkZ());
    }

    protected void readChunkLocation(ByteBuf buffer, LevelChunkPacket packet) {
        packet.setChunkX(VarInts.readInt(buffer));
        packet.setChunkZ(VarInts.readInt(buffer));
    }
}
