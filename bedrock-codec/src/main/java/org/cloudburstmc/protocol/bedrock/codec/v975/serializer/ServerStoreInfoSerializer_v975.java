package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerStoreInfoPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerStoreInfoSerializer_v975 implements BedrockPacketSerializer<ServerStoreInfoPacket> {
    public static final ServerStoreInfoSerializer_v975 INSTANCE = new ServerStoreInfoSerializer_v975();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerStoreInfoPacket packet) {
        helper.writeOptionalNull(buffer, packet.getClientStoreEntryPointConfiguration(), helper::writeClientStoreEntryPointConfig);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerStoreInfoPacket packet) {
        helper.readOptional(buffer, null, helper::readClientStoreEntryPointConfig);
    }
}