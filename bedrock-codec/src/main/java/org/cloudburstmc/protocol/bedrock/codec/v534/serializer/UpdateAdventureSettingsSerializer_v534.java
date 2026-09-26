package org.cloudburstmc.protocol.bedrock.codec.v534.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.world.AdventureSettings;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAdventureSettingsPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateAdventureSettingsSerializer_v534 implements BedrockPacketSerializer<UpdateAdventureSettingsPacket> {
    public static final UpdateAdventureSettingsSerializer_v534 INSTANCE = new UpdateAdventureSettingsSerializer_v534();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAdventureSettingsPacket packet) {
        AdventureSettings settings = packet.getAdventureSettings();
        buffer.writeBoolean(settings.isNoPvm());
        buffer.writeBoolean(settings.isNoMvp());
        buffer.writeBoolean(settings.isImmutableWorld());
        buffer.writeBoolean(settings.isShowNameTags());
        buffer.writeBoolean(settings.isAutoJump());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAdventureSettingsPacket packet) {
        AdventureSettings settings = new AdventureSettings();
        settings.setNoPvm(buffer.readBoolean());
        settings.setNoMvp(buffer.readBoolean());
        settings.setImmutableWorld(buffer.readBoolean());
        settings.setShowNameTags(buffer.readBoolean());
        settings.setAutoJump(buffer.readBoolean());
        packet.setAdventureSettings(settings);
    }
}
