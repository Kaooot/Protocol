package org.cloudburstmc.protocol.bedrock.codec.v662.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v618.serializer.ResourcePacksInfoSerializer_v618;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v622 extends ResourcePacksInfoSerializer_v618 {
    public static final ResourcePacksInfoSerializer_v622 INSTANCE = new ResourcePacksInfoSerializer_v622();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasAddonPacks());
        buffer.writeBoolean(packet.isHasScripts());
        buffer.writeBoolean(false); // force server packs enabled
        buffer.writeShortLE(0);
        helper.writeArray(buffer, packet.getResourcePacks(), ByteBuf::writeShortLE, this::writePackInfoData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasAddonPacks(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        buffer.readBoolean();
        buffer.readShortLE();
        helper.readArray(buffer, packet.getResourcePacks(), ByteBuf::readShortLE, this::readPackInfoData);
    }
}
