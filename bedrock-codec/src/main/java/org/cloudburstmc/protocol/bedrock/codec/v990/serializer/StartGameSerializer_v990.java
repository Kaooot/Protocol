package org.cloudburstmc.protocol.bedrock.codec.v990.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.StartGameSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.LevelSettings;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v990 extends StartGameSerializer_v944 {
    public static final StartGameSerializer_v990 INSTANCE = new StartGameSerializer_v990();

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.writeLevelSettings(buffer, helper, settings);
        VarInts.writeInt(buffer, settings.getServerEditorConnectionPolicy());
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.readLevelSettings(buffer, helper, settings);
        settings.setServerEditorConnectionPolicy(VarInts.readInt(buffer));
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
}