package org.cloudburstmc.protocol.bedrock.codec.v465.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.EducationSettingsSerializer_v407;
import org.cloudburstmc.protocol.bedrock.packet.EducationSettingsPacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationSettingsSerializer_v465 extends EducationSettingsSerializer_v407 {
    public static final EducationSettingsSerializer_v465 INSTANCE = new EducationSettingsSerializer_v465();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
        helper.writeString(buffer, packet.getEducationLevelSettings().getCodeBuilderDefaultURI());
        helper.writeString(buffer, packet.getEducationLevelSettings().getCodeBuilderTitle());
        buffer.writeBoolean(packet.getEducationLevelSettings().isCanResizeCodeBuilder());
        buffer.writeBoolean(packet.getEducationLevelSettings().isDisableLegacyTitleBar());
        helper.writeString(buffer, packet.getEducationLevelSettings().getPostProcessFilter());
        helper.writeString(buffer, packet.getEducationLevelSettings().getScreenshotBorderResourcePath());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getEducationLevelSettings().getAgentCapabilities(),
                (byteBuf, optional) -> byteBuf.writeBoolean(optional.getAsBoolean()));
        helper.writeOptional(buffer, Optional::isPresent, packet.getEducationLevelSettings().getCodeBuilderOverrideUri(),
                (byteBuf, optional) -> helper.writeString(byteBuf, optional.get()));
        buffer.writeBoolean(packet.getEducationLevelSettings().isQuizAttached());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getEducationLevelSettings().getExternalLinkSettings(),
                (byteBuf, optional) -> byteBuf.writeBoolean(optional.getAsBoolean()));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
        packet.getEducationLevelSettings().setCodeBuilderDefaultURI(helper.readString(buffer));
        packet.getEducationLevelSettings().setCodeBuilderTitle(helper.readString(buffer));
        packet.getEducationLevelSettings().setCanResizeCodeBuilder(buffer.readBoolean());
        packet.getEducationLevelSettings().setDisableLegacyTitleBar(buffer.readBoolean());
        packet.getEducationLevelSettings().setPostProcessFilter(helper.readString(buffer));
        packet.getEducationLevelSettings().setScreenshotBorderResourcePath(helper.readString(buffer));
        packet.getEducationLevelSettings().setAgentCapabilities(helper.readOptional(buffer, OptionalBoolean.empty(),
                byteBuf -> OptionalBoolean.of(buffer.readBoolean())));
        packet.getEducationLevelSettings().setCodeBuilderOverrideUri(helper.readOptional(buffer, Optional.empty(), byteBuf -> Optional.of(helper.readString(byteBuf))));
        packet.getEducationLevelSettings().setQuizAttached(buffer.readBoolean());
        packet.getEducationLevelSettings().setExternalLinkSettings(helper.readOptional(buffer, OptionalBoolean.empty(),
                byteBuf -> OptionalBoolean.of(buffer.readBoolean())));
    }
}
