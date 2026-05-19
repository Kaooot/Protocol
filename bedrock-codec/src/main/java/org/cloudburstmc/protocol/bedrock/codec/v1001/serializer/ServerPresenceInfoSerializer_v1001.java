package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.ServerPresenceInfoSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerPresenceInfoSerializer_v1001 extends ServerPresenceInfoSerializer_v975 {
    public static final ServerPresenceInfoSerializer_v1001 INSTANCE = new ServerPresenceInfoSerializer_v1001();

    @Override
    protected void writePresenceConfiguration(ByteBuf buffer, BedrockCodecHelper helper, PresenceConfiguration configuration) {
        super.writePresenceConfiguration(buffer, helper, configuration);
        helper.writeString(buffer, configuration.getRichPresenceId());
    }

    @Override
    protected PresenceConfiguration readPresenceConfiguration(ByteBuf buffer, BedrockCodecHelper helper) {
        final PresenceConfiguration configuration = new PresenceConfiguration();
        configuration.setExperienceName(helper.readString(buffer));
        configuration.setWorldName(helper.readString(buffer));
        configuration.setRichPresenceId(helper.readString(buffer));
        return configuration;
    }
}