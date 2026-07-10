package org.cloudburstmc.protocol.bedrock.codec.v766.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v748.serializer.ResourcePacksInfoSerializer_v748;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackIdVersion;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

public class ResourcePacksInfoSerializer_v766 extends ResourcePacksInfoSerializer_v748 {
    public static final ResourcePacksInfoSerializer_v766 INSTANCE = new ResourcePacksInfoSerializer_v766();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasAddonPacks());
        buffer.writeBoolean(packet.isHasScripts());
        this.writePackIdVersion(buffer, helper, packet.getWorldTemplateIdAndVersion());
        helper.writeArray(buffer, packet.getResourcePacks(), ByteBuf::writeShortLE, this::writePackInfoData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasAddonPacks(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        packet.setWorldTemplateIdAndVersion(this.readPackIdVersion(buffer, helper));
        helper.readArray(buffer, packet.getResourcePacks(), ByteBuf::readShortLE, this::readPackInfoData);
    }

    @Override
    protected void writePackIdVersion(ByteBuf buffer, BedrockCodecHelper helper, PackIdVersion packIdVersion) {
        helper.writeUuid(buffer, packIdVersion.getPackUUID());
        helper.writeString(buffer, packIdVersion.getPackVersion());
    }

    @Override
    protected PackIdVersion readPackIdVersion(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackIdVersion packIdVersion = new PackIdVersion();
        packIdVersion.setPackUUID(helper.readUuid(buffer));
        packIdVersion.setPackVersion(helper.readString(buffer));
        return packIdVersion;
    }
}