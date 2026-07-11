package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ResourcePackClientResponseSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackResponse;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackClientResponseSerializer_v2168 extends ResourcePackClientResponseSerializer_v291 {
    public static final ResourcePackClientResponseSerializer_v2168 INSTANCE = new ResourcePackClientResponseSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackClientResponsePacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getResponse().ordinal());
        helper.writeString(buffer, packet.getResponse().getId());
        if (packet.getResponse().equals(ResourcePackResponse.DOWNLOADING)) {
            helper.writeArray(buffer, packet.getDownloadingPacks(), helper::writeString);
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackClientResponsePacket packet) {
        packet.setResponse(ResourcePackResponse.from(VarInts.readUnsignedInt(buffer)));
        helper.readString(buffer);
        if (packet.getResponse().equals(ResourcePackResponse.DOWNLOADING)) {
            helper.readArray(buffer, packet.getDownloadingPacks(), helper::readString);
        }
    }
}