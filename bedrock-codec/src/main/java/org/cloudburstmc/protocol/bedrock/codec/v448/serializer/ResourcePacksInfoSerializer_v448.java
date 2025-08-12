package org.cloudburstmc.protocol.bedrock.codec.v448.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v422.serializer.ResourcePacksInfoSerializer_v422;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v448 extends ResourcePacksInfoSerializer_v422 {

    public static final ResourcePacksInfoSerializer_v448 INSTANCE = new ResourcePacksInfoSerializer_v448();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasScripts());
        buffer.writeBoolean(packet.isForceServerPacksEnabled());
        writePacks(buffer, packet.getBehaviorPacks(), helper, false);
        writePacks(buffer, packet.getResourcePacks(), helper, true);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        packet.setForceServerPacksEnabled(buffer.readBoolean());
        readPacks(buffer, packet.getBehaviorPacks(), helper, false);
        readPacks(buffer, packet.getResourcePacks(), helper, true);
    }
}
