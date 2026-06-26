package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.ResourcePacksInfoSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackIdVersion;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackInfoData;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v2164 extends ResourcePacksInfoSerializer_v818 {
    public static final ResourcePacksInfoSerializer_v2164 INSTANCE = new ResourcePacksInfoSerializer_v2164();

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
        helper.readArray(buffer, packet.getResourcePacks(), this::readPackInfoData);
    }

    protected void writePackInfoData(ByteBuf buffer, BedrockCodecHelper helper, PackInfoData data) {
        this.writePackIdVersion(buffer, helper, data.getPackIdVersion());
        buffer.writeLongLE(data.getPackSize());
        helper.writeString(buffer, data.getContentKey());
        helper.writeString(buffer, data.getSubpackName());
        helper.writeString(buffer, data.getContentIdentity());
        buffer.writeBoolean(data.isHasScripts());
        buffer.writeBoolean(data.isAddonPack());
        buffer.writeBoolean(data.isRayTracingCapable());
        helper.writeString(buffer, data.getCdnUrl());
    }

    protected PackInfoData readPackInfoData(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackInfoData data = new PackInfoData();
        data.setPackIdVersion(this.readPackIdVersion(buffer, helper));
        data.setContentKey(helper.readString(buffer));
        data.setSubpackName(helper.readString(buffer));
        data.setContentIdentity(helper.readString(buffer));
        data.setHasScripts(buffer.readBoolean());
        data.setAddonPack(buffer.readBoolean());
        data.setRayTracingCapable(buffer.readBoolean());
        data.setCdnUrl(helper.readString(buffer));
        return data;
    }

    protected void writePackIdVersion(ByteBuf buffer, BedrockCodecHelper helper, PackIdVersion packIdVersion) {
        helper.writeUuid(buffer, packIdVersion.getPackUUID());
        helper.writeString(buffer, packIdVersion.getPackVersion());
    }

    protected PackIdVersion readPackIdVersion(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackIdVersion packIdVersion = new PackIdVersion();
        packIdVersion.setPackUUID(helper.readUuid(buffer));
        packIdVersion.setPackVersion(helper.readString(buffer));
        return packIdVersion;
    }
}