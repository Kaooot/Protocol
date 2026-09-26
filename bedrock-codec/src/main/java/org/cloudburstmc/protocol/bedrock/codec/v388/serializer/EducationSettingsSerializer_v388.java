package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.education.EducationLevelSettings;
import org.cloudburstmc.protocol.bedrock.packet.EducationSettingsPacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationSettingsSerializer_v388 implements BedrockPacketSerializer<EducationSettingsPacket> {

    public static final EducationSettingsSerializer_v388 INSTANCE = new EducationSettingsSerializer_v388();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
        this.writeEducationLevelSettings(buffer, helper, packet.getEducationLevelSettings());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
        packet.setEducationLevelSettings(this.readEducationLevelSettings(buffer, helper));
    }

    protected void writeEducationLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, EducationLevelSettings settings) {
        helper.writeString(buffer, settings.getCodeBuilderDefaultURI());
        buffer.writeBoolean(settings.isDeprecatedAlwaysFalse());
    }

    protected EducationLevelSettings readEducationLevelSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final EducationLevelSettings settings = new EducationLevelSettings();
        settings.setCodeBuilderDefaultURI(helper.readString(buffer));
        settings.setDeprecatedAlwaysFalse(buffer.readBoolean());
        return settings;
    }
}