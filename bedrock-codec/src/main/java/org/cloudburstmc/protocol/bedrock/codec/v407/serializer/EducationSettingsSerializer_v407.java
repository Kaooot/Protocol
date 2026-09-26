package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.EducationSettingsSerializer_v388;
import org.cloudburstmc.protocol.bedrock.data.education.EducationLevelSettings;
import org.cloudburstmc.protocol.bedrock.data.education.EducationLocalLevelSettings;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationSettingsSerializer_v407 extends EducationSettingsSerializer_v388 {

    public static final EducationSettingsSerializer_v407 INSTANCE = new EducationSettingsSerializer_v407();

    @Override
    protected void writeEducationLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, EducationLevelSettings settings) {
        helper.writeString(buffer, settings.getCodeBuilderDefaultURI());
        helper.writeString(buffer, settings.getCodeBuilderTitle());
        buffer.writeBoolean(settings.isCanResizeCodeBuilder());
        helper.writeOptionalNull(buffer, settings.getLocalSettings(), this::writeEducationLocalLevelSettings);
        buffer.writeBoolean(settings.isDeprecatedAlwaysFalse());
    }

    @Override
    protected EducationLevelSettings readEducationLevelSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final EducationLevelSettings settings = new EducationLevelSettings();
        settings.setCodeBuilderDefaultURI(helper.readString(buffer));
        settings.setCodeBuilderTitle(helper.readString(buffer));
        settings.setCanResizeCodeBuilder(buffer.readBoolean());
        settings.setLocalSettings(helper.readOptional(buffer, null, this::readEducationLocalLevelSettings));
        settings.setDeprecatedAlwaysFalse(buffer.readBoolean());
        return settings;
    }

    protected void writeEducationLocalLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, EducationLocalLevelSettings localLevelSettings) {
        helper.writeString(buffer, localLevelSettings.getCodeBuilderOverrideUri());
    }

    protected EducationLocalLevelSettings readEducationLocalLevelSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final EducationLocalLevelSettings localLevelSettings = new EducationLocalLevelSettings();
        localLevelSettings.setCodeBuilderOverrideUri(helper.readString(buffer));
        return localLevelSettings;
    }
}