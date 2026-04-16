package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.ClientStoreEntryPointConfiguration;
import org.cloudburstmc.protocol.bedrock.packet.ServerStoreInfoPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerStoreInfoSerializer_v975 implements BedrockPacketSerializer<ServerStoreInfoPacket> {
    public static final ServerStoreInfoSerializer_v975 INSTANCE = new ServerStoreInfoSerializer_v975();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerStoreInfoPacket packet) {
        helper.writeOptionalNull(buffer, packet.getClientStoreEntryPointConfiguration(), this::writeClientStoreEntryPointConfiguration);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerStoreInfoPacket packet) {
        helper.readOptional(buffer, null, this::readClientStoreEntryPointConfiguration);
    }

    protected void writeClientStoreEntryPointConfiguration(ByteBuf buffer, BedrockCodecHelper helper, ClientStoreEntryPointConfiguration configuration) {
        helper.writeString(buffer, configuration.getStoreId());
        helper.writeString(buffer, configuration.getStoreName());
    }

    protected ClientStoreEntryPointConfiguration readClientStoreEntryPointConfiguration(ByteBuf buffer, BedrockCodecHelper helper) {
        final ClientStoreEntryPointConfiguration configuration = new ClientStoreEntryPointConfiguration();
        configuration.setStoreId(helper.readString(buffer));
        configuration.setStoreName(helper.readString(buffer));
        return configuration;
    }
}