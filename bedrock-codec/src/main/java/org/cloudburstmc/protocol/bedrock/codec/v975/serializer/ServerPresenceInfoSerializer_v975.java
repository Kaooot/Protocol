package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;
import org.cloudburstmc.protocol.bedrock.packet.ServerPresenceInfoPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerPresenceInfoSerializer_v975 implements BedrockPacketSerializer<ServerPresenceInfoPacket> {
    public static final ServerPresenceInfoSerializer_v975 INSTANCE = new ServerPresenceInfoSerializer_v975();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerPresenceInfoPacket packet) {
        helper.writeOptionalNull(buffer, packet.getPresenceConfiguration(), this::writePresenceConfiguration);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerPresenceInfoPacket packet) {
        packet.setPresenceConfiguration(helper.readOptional(buffer, null, this::readPresenceConfiguration));
    }

    protected void writePresenceConfiguration(ByteBuf buffer, BedrockCodecHelper helper, PresenceConfiguration configuration) {
        helper.writeString(buffer, configuration.getExperienceName());
        helper.writeString(buffer, configuration.getWorldName());
    }

    protected PresenceConfiguration readPresenceConfiguration(ByteBuf buffer, BedrockCodecHelper helper) {
        final PresenceConfiguration configuration = new PresenceConfiguration();
        configuration.setExperienceName(helper.readString(buffer));
        configuration.setWorldName(helper.readString(buffer));
        return configuration;
    }
}