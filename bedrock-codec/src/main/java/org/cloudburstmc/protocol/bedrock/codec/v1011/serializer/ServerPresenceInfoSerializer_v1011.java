package org.cloudburstmc.protocol.bedrock.codec.v1011.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.ServerPresenceInfoSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerPresenceInfoSerializer_v1011 extends ServerPresenceInfoSerializer_v1001 {
    public static final ServerPresenceInfoSerializer_v1011 INSTANCE = new ServerPresenceInfoSerializer_v1011();

    @Override
    protected void writePresenceConfiguration(ByteBuf buffer, BedrockCodecHelper helper, PresenceConfiguration configuration) {
        helper.writeString(buffer, configuration.getRichPresenceId());
    }

    @Override
    protected PresenceConfiguration readPresenceConfiguration(ByteBuf buffer, BedrockCodecHelper helper) {
        final PresenceConfiguration configuration = new PresenceConfiguration();
        configuration.setRichPresenceId(helper.readString(buffer));
        return configuration;
    }
}