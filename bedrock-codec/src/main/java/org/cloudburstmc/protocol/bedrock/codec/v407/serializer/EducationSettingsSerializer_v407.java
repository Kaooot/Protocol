package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.EducationSettingsPacket;

import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationSettingsSerializer_v407 implements BedrockPacketSerializer<EducationSettingsPacket> {

    public static final EducationSettingsSerializer_v407 INSTANCE = new EducationSettingsSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
        helper.writeString(buffer, packet.getEducationLevelSettings().getCodeBuilderDefaultURI());
        helper.writeString(buffer, packet.getEducationLevelSettings().getCodeBuilderTitle());
        buffer.writeBoolean(packet.getEducationLevelSettings().isCanResizeCodeBuilder());
        helper.writeOptional(buffer, Optional::isPresent, packet.getEducationLevelSettings().getCodeBuilderOverrideUri(),
                (byteBuf, optional) -> helper.writeString(byteBuf, optional.get()));
        buffer.writeBoolean(packet.getEducationLevelSettings().isQuizAttached());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
        packet.getEducationLevelSettings().setCodeBuilderDefaultURI(helper.readString(buffer));
        packet.getEducationLevelSettings().setCodeBuilderTitle(helper.readString(buffer));
        packet.getEducationLevelSettings().setCanResizeCodeBuilder(buffer.readBoolean());
        packet.getEducationLevelSettings().setCodeBuilderOverrideUri(helper.readOptional(buffer, Optional.empty(), byteBuf -> Optional.of(helper.readString(byteBuf))));
        packet.getEducationLevelSettings().setQuizAttached(buffer.readBoolean());
    }
}
