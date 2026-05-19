package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.StartGameSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.LevelSettings;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v1001 extends StartGameSerializer_v944 {
    public static final StartGameSerializer_v1001 INSTANCE = new StartGameSerializer_v1001();

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.writeLevelSettings(buffer, helper, settings);
        VarInts.writeInt(buffer, settings.getServerEditorConnectionPolicy());
        buffer.writeBoolean(settings.isAllowAnonymousBlockDropsInEditorWorlds());
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.readLevelSettings(buffer, helper, settings);
        settings.setServerEditorConnectionPolicy(VarInts.readInt(buffer));
        settings.setAllowAnonymousBlockDropsInEditorWorlds(buffer.readBoolean());
    }

    @Override
    protected void writePresenceInfo(ByteBuf buffer, BedrockCodecHelper helper, PresenceConfiguration info) {
        super.writePresenceInfo(buffer, helper, info);
        helper.writeString(buffer, info.getRichPresenceId());
    }

    @Override
    protected PresenceConfiguration readPresenceInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final PresenceConfiguration info = new PresenceConfiguration();
        info.setExperienceName(helper.readString(buffer));
        info.setWorldName(helper.readString(buffer));
        info.setRichPresenceId(helper.readString(buffer));
        return info;
    }

    @Override
    protected void writeBeforeNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        super.writeBeforeNetworkPermissions(buffer, helper, packet);
        buffer.writeBoolean(packet.isLoggingChat());
    }

    @Override
    protected void readBeforeNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        super.readBeforeNetworkPermissions(buffer, helper, packet);
        packet.setLoggingChat(buffer.readBoolean());
    }
}