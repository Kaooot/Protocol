package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v766.serializer.ResourcePacksInfoSerializer_v766;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

public class ResourcePacksInfoSerializer_v818 extends ResourcePacksInfoSerializer_v766 {

    public static final ResourcePacksInfoSerializer_v818 INSTANCE = new ResourcePacksInfoSerializer_v818();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasAddonPacks());
        buffer.writeBoolean(packet.isHasScripts());
        buffer.writeBoolean(packet.isForceDisableVibrantVisuals());
        this.writePackIdVersion(buffer, helper, packet.getWorldTemplateIdAndVersion());
        helper.writeArray(buffer, packet.getResourcePacks(), ByteBuf::writeShortLE, this::writePackInfoData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasAddonPacks(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        packet.setForceDisableVibrantVisuals(buffer.readBoolean());
        packet.setWorldTemplateIdAndVersion(this.readPackIdVersion(buffer, helper));
        helper.readArray(buffer, packet.getResourcePacks(), ByteBuf::readShortLE, this::readPackInfoData, MAX_LENGTH);
    }
}