package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.ClientCacheBlobStatusSerializer_v361;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheBlobStatusPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientCacheBlobStatusSerializer_v1001 extends ClientCacheBlobStatusSerializer_v361 {
    public static final ClientCacheBlobStatusSerializer_v1001 INSTANCE = new ClientCacheBlobStatusSerializer_v1001();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheBlobStatusPacket packet) {
        helper.writeArray(buffer, packet.getMissingIds(), ByteBuf::writeLongLE);
        helper.writeArray(buffer, packet.getFoundIds(), ByteBuf::writeLongLE);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheBlobStatusPacket packet) {
        helper.readArray(buffer, packet.getMissingIds(), ByteBuf::readLongLE, MAX_LENGTH);
        helper.readArray(buffer, packet.getFoundIds(), ByteBuf::readLongLE, MAX_LENGTH);
    }
}