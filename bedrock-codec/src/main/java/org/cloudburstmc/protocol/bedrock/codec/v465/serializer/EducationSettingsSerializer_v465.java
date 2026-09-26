package org.cloudburstmc.protocol.bedrock.codec.v465.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.EducationSettingsSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.education.ExternalLinkSettings;
import org.cloudburstmc.protocol.bedrock.data.education.AgentCapabilities;
import org.cloudburstmc.protocol.bedrock.data.education.EducationLevelSettings;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationSettingsSerializer_v465 extends EducationSettingsSerializer_v407 {
    public static final EducationSettingsSerializer_v465 INSTANCE = new EducationSettingsSerializer_v465();

    @Override
    protected void writeEducationLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, EducationLevelSettings settings) {
        helper.writeString(buffer, settings.getCodeBuilderDefaultURI());
        helper.writeString(buffer, settings.getCodeBuilderTitle());
        buffer.writeBoolean(settings.isCanResizeCodeBuilder());
        buffer.writeBoolean(settings.isDisableLegacyTitleBar());
        helper.writeString(buffer, settings.getPostProcessFilter());
        helper.writeString(buffer, settings.getScreenshotBorderResourcePath());
        this.writeAgentCapabilities(buffer, helper, settings.getAgentCapabilities());
        helper.writeOptionalNull(buffer, settings.getLocalSettings(), this::writeEducationLocalLevelSettings);
        buffer.writeBoolean(settings.isDeprecatedAlwaysFalse());
        helper.writeOptionalNull(buffer, settings.getExternalLinkSettings(), this::writeExternalLinkSettings);
    }

    @Override
    protected EducationLevelSettings readEducationLevelSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final EducationLevelSettings settings = new EducationLevelSettings();
        settings.setCodeBuilderDefaultURI(helper.readString(buffer));
        settings.setCodeBuilderTitle(helper.readString(buffer));
        settings.setCanResizeCodeBuilder(buffer.readBoolean());
        settings.setDisableLegacyTitleBar(buffer.readBoolean());
        settings.setPostProcessFilter(helper.readString(buffer));
        settings.setScreenshotBorderResourcePath(helper.readString(buffer));
        settings.setAgentCapabilities(this.readAgentCapabilities(buffer, helper));
        settings.setLocalSettings(helper.readOptional(buffer, null, this::readEducationLocalLevelSettings));
        settings.setDeprecatedAlwaysFalse(buffer.readBoolean());
        settings.setExternalLinkSettings(helper.readOptional(buffer, null, this::readExternalLinkSettings));
        return settings;
    }

    protected void writeAgentCapabilities(ByteBuf buffer, BedrockCodecHelper helper, AgentCapabilities agentCapabilities) {
        helper.writeOptional(
                buffer,
                OptionalBoolean::isPresent,
                agentCapabilities.getCanModifyBlocks(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean())
        );
    }

    protected AgentCapabilities readAgentCapabilities(ByteBuf buffer, BedrockCodecHelper helper) {
        final AgentCapabilities agentCapabilities = new AgentCapabilities();
        agentCapabilities.setCanModifyBlocks(
                helper.readOptional(
                        buffer,
                        OptionalBoolean.empty(),
                        buf -> OptionalBoolean.of(buf.readBoolean())
                )
        );
        return agentCapabilities;
    }

    protected void writeExternalLinkSettings(ByteBuf buffer, BedrockCodecHelper helper, ExternalLinkSettings settings) {
        helper.writeString(buffer, settings.getURL());
        helper.writeString(buffer, settings.getDisplayName());
    }

    protected ExternalLinkSettings readExternalLinkSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final ExternalLinkSettings settings = new ExternalLinkSettings();
        settings.setURL(helper.readString(buffer));
        settings.setDisplayName(helper.readString(buffer));
        return settings;
    }
}