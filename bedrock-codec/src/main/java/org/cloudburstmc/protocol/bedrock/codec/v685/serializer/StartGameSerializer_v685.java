package org.cloudburstmc.protocol.bedrock.codec.v685.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v671.serializer.StartGameSerializer_v671;
import org.cloudburstmc.protocol.bedrock.data.LevelSettings;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v685 extends StartGameSerializer_v671 {
    public static final StartGameSerializer_v685 INSTANCE = new StartGameSerializer_v685();

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.writeLevelSettings(buffer, helper, settings);
        helper.writeString(buffer, settings.getServerId());
        helper.writeString(buffer, settings.getWorldId());
        helper.writeString(buffer, settings.getScenarioId());
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.readLevelSettings(buffer, helper, settings);
        settings.setServerId(helper.readString(buffer));
        settings.setWorldId(helper.readString(buffer));
        settings.setScenarioId(helper.readString(buffer));
    }
}