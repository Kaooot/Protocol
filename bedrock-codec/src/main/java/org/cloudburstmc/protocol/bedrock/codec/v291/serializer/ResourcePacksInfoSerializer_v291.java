package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackIdVersion;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackInfoData;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v291 implements BedrockPacketSerializer<ResourcePacksInfoPacket> {
    public static final ResourcePacksInfoSerializer_v291 INSTANCE = new ResourcePacksInfoSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeShortLE(0);
        helper.writeArray(buffer, packet.getResourcePacks(), ByteBuf::writeShortLE, this::writePackInfoData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        buffer.readShortLE();
        helper.readArray(buffer, packet.getResourcePacks(), ByteBuf::readShortLE, this::readPackInfoData);
    }

    protected void writePackInfoData(ByteBuf buffer, BedrockCodecHelper helper, PackInfoData data) {
        this.writePackIdVersion(buffer, helper, data.getPackIdVersion());
        buffer.writeLongLE(data.getPackSize());
        helper.writeString(buffer, data.getContentKey());
        helper.writeString(buffer, data.getSubpackName());
        helper.writeString(buffer, data.getContentIdentity());
    }

    protected PackInfoData readPackInfoData(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackInfoData data = new PackInfoData();
        data.setPackIdVersion(this.readPackIdVersion(buffer, helper));
        data.setPackSize(buffer.readLongLE());
        data.setContentKey(helper.readString(buffer));
        data.setSubpackName(helper.readString(buffer));
        data.setContentIdentity(helper.readString(buffer));
        return data;
    }

    protected void writePackIdVersion(ByteBuf buffer, BedrockCodecHelper helper, PackIdVersion packIdVersion) {
        helper.writeString(buffer, packIdVersion.getPackUUID().toString());
        helper.writeString(buffer, packIdVersion.getPackVersion());
    }

    protected PackIdVersion readPackIdVersion(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackIdVersion packIdVersion = new PackIdVersion();
        packIdVersion.setPackUUID(UUID.fromString(helper.readString(buffer)));
        packIdVersion.setPackVersion(helper.readString(buffer));
        return packIdVersion;
    }
}