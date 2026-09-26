package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackDataInfoPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackDataInfoSerializer_v291 implements BedrockPacketSerializer<ResourcePackDataInfoPacket> {
    public static final ResourcePackDataInfoSerializer_v291 INSTANCE = new ResourcePackDataInfoSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackDataInfoPacket packet) {
        String packInfo = packet.getPackId().toString() + (packet.getPackVersion() == null ? "" : '_' + packet.getPackVersion());
        helper.writeString(buffer, packInfo);
        buffer.writeIntLE((int) packet.getChunkSize());
        buffer.writeIntLE((int) packet.getNumberOfChunks());
        buffer.writeLongLE(packet.getFileSize());
        byte[] hash = packet.getFileHash();
        VarInts.writeUnsignedInt(buffer, hash.length);
        buffer.writeBytes(hash);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackDataInfoPacket packet) {
        String[] packInfo = helper.readString(buffer).split("_");
        packet.setPackId(UUID.fromString(packInfo[0]));
        if (packInfo.length > 1) {
            packet.setPackVersion(packInfo[1]);
        }
        packet.setChunkSize(buffer.readIntLE());
        packet.setNumberOfChunks(buffer.readIntLE());
        packet.setFileSize(buffer.readLongLE());
        byte[] hash = new byte[VarInts.readUnsignedInt(buffer)];
        buffer.readBytes(hash);
        packet.setFileHash(hash);
    }
}
