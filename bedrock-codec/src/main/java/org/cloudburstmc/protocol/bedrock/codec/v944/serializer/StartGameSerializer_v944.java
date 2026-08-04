package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.StartGameSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.payload.common.ServerConfigurationJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.ClientStoreEntryPointConfiguration;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v944 extends StartGameSerializer_v924 {
    public static final StartGameSerializer_v944 INSTANCE = new StartGameSerializer_v944();

    @Override
    protected void writeServerConfigurationJoinInfo(ByteBuf buffer, BedrockCodecHelper helper, ServerConfigurationJoinInfo joinInfo) {
        helper.writeOptionalNull(buffer, joinInfo.getGatheringsConfig(), helper::writeGatheringsConfigurationJoinInfo);
        helper.writeOptionalNull(buffer, joinInfo.getStoreEntryPointInfo(), this::writeStoreEntryPointInfo);
        helper.writeOptionalNull(buffer, joinInfo.getPresenceConfiguration(), helper::writePresenceConfiguration);
    }

    @Override
    protected ServerConfigurationJoinInfo readServerConfigurationJoinInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ServerConfigurationJoinInfo joinInfo = new ServerConfigurationJoinInfo();
        joinInfo.setGatheringsConfig(helper.readOptional(buffer, null, helper::readGatheringsConfigurationJoinInfo));
        joinInfo.setStoreEntryPointInfo(helper.readOptional(buffer, null, this::readStoreEntryPointInfo));
        joinInfo.setPresenceConfiguration(helper.readOptional(buffer, null, helper::readPresenceConfiguration));
        return joinInfo;
    }

    protected void writeStoreEntryPointInfo(ByteBuf buffer, BedrockCodecHelper helper, ClientStoreEntryPointConfiguration info) {
        helper.writeString(buffer, info.getStoreId());
        helper.writeString(buffer, info.getStoreName());
    }

    protected ClientStoreEntryPointConfiguration readStoreEntryPointInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ClientStoreEntryPointConfiguration info = new ClientStoreEntryPointConfiguration();
        info.setStoreId(helper.readString(buffer));
        info.setStoreName(helper.readString(buffer));
        return info;
    }
}