package org.cloudburstmc.protocol.bedrock.codec.v332.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ResourcePacksInfoSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackInfoData;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v332 extends ResourcePacksInfoSerializer_v291 {
    public static final ResourcePacksInfoSerializer_v332 INSTANCE = new ResourcePacksInfoSerializer_v332();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        buffer.writeBoolean(packet.isResourcePackRequired());
        buffer.writeBoolean(packet.isHasScripts());
        buffer.writeShortLE(0);
        helper.writeArray(buffer, packet.getResourcePacks(), ByteBuf::writeShortLE, this::writePackInfoData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
        packet.setResourcePackRequired(buffer.readBoolean());
        packet.setHasScripts(buffer.readBoolean());
        buffer.readShortLE();
        helper.readArray(buffer, packet.getResourcePacks(), ByteBuf::readShortLE, this::readPackInfoData, MAX_LENGTH);
    }

    @Override
    protected void writePackInfoData(ByteBuf buffer, BedrockCodecHelper helper, PackInfoData data) {
        super.writePackInfoData(buffer, helper, data);
        buffer.writeBoolean(data.isHasScripts());
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
        return data;
    }
}