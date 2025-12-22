package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v671.serializer.ResourcePackStackSerializer_v671;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackStackSerializer_v898 extends ResourcePackStackSerializer_v671 {
    public static final ResourcePackStackSerializer_v898 INSTANCE = new ResourcePackStackSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        buffer.writeBoolean(packet.isTexturePackRequired());
        helper.writeArray(buffer, packet.getTexturePackList(), this::writeEntry);
        helper.writeString(buffer, packet.getBaseGameVersion());
        helper.writeExperiments(buffer, packet.getExperiments());
        buffer.writeBoolean(packet.isWereAnyExperimentsEverToggled());
        buffer.writeBoolean(packet.isIncludeEditorPacks());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        packet.setTexturePackRequired(buffer.readBoolean());
        helper.readArray(buffer, packet.getTexturePackList(), this::readEntry);
        packet.setBaseGameVersion(helper.readString(buffer));
        helper.readExperiments(buffer, packet.getExperiments());
        packet.setWereAnyExperimentsEverToggled(buffer.readBoolean());
        packet.setIncludeEditorPacks(buffer.readBoolean());
    }
}