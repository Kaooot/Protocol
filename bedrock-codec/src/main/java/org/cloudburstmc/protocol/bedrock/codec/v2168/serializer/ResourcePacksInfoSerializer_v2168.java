package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.ResourcePacksInfoSerializer_v818;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v2168 extends ResourcePacksInfoSerializer_v818 {
    public static final ResourcePacksInfoSerializer_v2168 INSTANCE = new ResourcePacksInfoSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasAddonPacks());
        buffer.writeBoolean(packet.isHasScripts());
        buffer.writeBoolean(packet.isForceDisableVibrantVisuals());
        this.writePackIdVersion(buffer, helper, packet.getWorldTemplateIdAndVersion());
        helper.writeArray(buffer, packet.getResourcePacks(), this::writePackInfoData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasAddonPacks(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        packet.setForceDisableVibrantVisuals(buffer.readBoolean());
        packet.setWorldTemplateIdAndVersion(this.readPackIdVersion(buffer, helper));
        helper.readArray(buffer, packet.getResourcePacks(), this::readPackInfoData, MAX_LENGTH);
    }
}