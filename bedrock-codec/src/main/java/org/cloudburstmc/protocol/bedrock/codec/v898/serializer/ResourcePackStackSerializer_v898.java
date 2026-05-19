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
        helper.writeArray(buffer, packet.getTexturePackList(), this::writePackInstanceId);
        helper.writeString(buffer, packet.getBaseGameVersion());
        this.writeExperiments(buffer, helper, packet.getExperiments());
        buffer.writeBoolean(packet.isIncludeEditorPacks());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        packet.setTexturePackRequired(buffer.readBoolean());
        helper.readArray(buffer, packet.getTexturePackList(), this::readPackInstanceId);
        packet.setBaseGameVersion(helper.readString(buffer));
        packet.setExperiments(this.readExperiments(buffer, helper));
        packet.setIncludeEditorPacks(buffer.readBoolean());
    }
}