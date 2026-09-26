package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.chunk.MissingBlobData;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheMissResponsePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientCacheMissResponseSerializer_v361 implements BedrockPacketSerializer<ClientCacheMissResponsePacket> {
    public static final ClientCacheMissResponseSerializer_v361 INSTANCE = new ClientCacheMissResponseSerializer_v361();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheMissResponsePacket packet) {
        final List<MissingBlobData> blobs = packet.getMissingBlobs();
        VarInts.writeUnsignedInt(buffer, blobs.size());
        for (MissingBlobData missingBlobData : blobs) {
            buffer.writeLongLE(missingBlobData.getBlobId());
            helper.writeByteBuf(buffer, missingBlobData.getBlobData());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheMissResponsePacket packet) {
        final List<MissingBlobData> blobs = packet.getMissingBlobs();
        final int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            final MissingBlobData missingBlobData = new MissingBlobData();
            missingBlobData.setBlobId(buffer.readLongLE());
            missingBlobData.setBlobData(helper.readByteBuf(buffer));

            blobs.add(missingBlobData);
        }
    }
}