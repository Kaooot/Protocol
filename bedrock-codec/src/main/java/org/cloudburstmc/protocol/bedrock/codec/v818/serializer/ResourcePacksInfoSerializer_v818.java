package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v766.serializer.ResourcePacksInfoSerializer_v766;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

public class ResourcePacksInfoSerializer_v818 extends ResourcePacksInfoSerializer_v766 {

    public static final ResourcePacksInfoSerializer_v818 INSTANCE = new ResourcePacksInfoSerializer_v818();

    /*@Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasAddonPacks());
        buffer.writeBoolean(packet.isHasScripts());
        buffer.writeBoolean(packet.isForceDisableVibrantVisuals());
        helper.writeUuid(buffer, packet.getWorldTemplateUUID());
        helper.writeString(buffer, packet.getWorldTemplateVersion());
        writePacks(buffer, packet.getResourcePacks(), helper, true);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasAddonPacks(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        packet.setForceDisableVibrantVisuals(buffer.readBoolean());
        packet.setWorldTemplateUUID(helper.readUuid(buffer));
        packet.setWorldTemplateVersion(helper.readString(buffer));
        readPacks(buffer, packet.getResourcePacks(), helper, true);
    }*/
}
