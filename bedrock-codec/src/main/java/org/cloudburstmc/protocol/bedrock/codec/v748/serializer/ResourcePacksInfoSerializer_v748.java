package org.cloudburstmc.protocol.bedrock.codec.v748.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.ResourcePacksInfoSerializer_v729;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackInfoData;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v748 extends ResourcePacksInfoSerializer_v729 {
    public static final ResourcePacksInfoSerializer_v748 INSTANCE = new ResourcePacksInfoSerializer_v748();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasAddonPacks());
        buffer.writeBoolean(packet.isHasScripts());
        helper.writeArray(buffer, packet.getResourcePacks(), ByteBuf::writeShortLE, this::writePackInfoData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasAddonPacks(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        helper.readArray(buffer, packet.getResourcePacks(), ByteBuf::readShortLE, this::readPackInfoData, MAX_LENGTH);
    }

    @Override
    protected void writePackInfoData(ByteBuf buffer, BedrockCodecHelper helper, PackInfoData data) {
        super.writePackInfoData(buffer, helper, data);
        helper.writeString(buffer, data.getCdnUrl() == null ? "" : data.getCdnUrl());
    }

    @Override
    protected PackInfoData readPackInfoData(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackInfoData data = new PackInfoData();
        data.setPackIdVersion(this.readPackIdVersion(buffer, helper));
        data.setPackSize(buffer.readLongLE());
        data.setContentKey(helper.readString(buffer));
        data.setSubpackName(helper.readString(buffer));
        data.setContentIdentity(helper.readString(buffer));
        data.setHasScripts(buffer.readBoolean());
        data.setAddonPack(buffer.readBoolean());
        data.setRayTracingCapable(buffer.readBoolean());
        data.setCdnUrl(helper.readString(buffer));
        return data;
    }
}