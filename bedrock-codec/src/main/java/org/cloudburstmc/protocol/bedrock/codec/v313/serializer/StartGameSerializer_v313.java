package org.cloudburstmc.protocol.bedrock.codec.v313.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.StartGameSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.LevelSettings;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v313 extends StartGameSerializer_v291 {
    public static final StartGameSerializer_v313 INSTANCE = new StartGameSerializer_v313();

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.writeLevelSettings(buffer, helper, settings);
        buffer.writeBoolean(settings.isFromWorldTemplate());
        buffer.writeBoolean(settings.isFromLockedWorldTemplate());
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.readLevelSettings(buffer, helper, settings);
        settings.setFromWorldTemplate(buffer.readBoolean());
        settings.setFromLockedWorldTemplate(buffer.readBoolean());
    }
}
