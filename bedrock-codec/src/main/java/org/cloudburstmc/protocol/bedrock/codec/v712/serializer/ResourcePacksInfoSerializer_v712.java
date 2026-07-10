package org.cloudburstmc.protocol.bedrock.codec.v712.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v662.serializer.ResourcePacksInfoSerializer_v622;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackInfoData;

public class ResourcePacksInfoSerializer_v712 extends ResourcePacksInfoSerializer_v622 {
    public static final ResourcePacksInfoSerializer_v712 INSTANCE = new ResourcePacksInfoSerializer_v712();

    @Override
    protected void writePackInfoData(ByteBuf buffer, BedrockCodecHelper helper, PackInfoData data) {
        this.writePackIdVersion(buffer, helper, data.getPackIdVersion());
        buffer.writeLongLE(data.getPackSize());
        helper.writeString(buffer, data.getContentKey());
        helper.writeString(buffer, data.getSubpackName());
        helper.writeString(buffer, data.getContentIdentity());
        buffer.writeBoolean(data.isHasScripts());
        buffer.writeBoolean(data.isAddonPack()); // added
        buffer.writeBoolean(data.isRayTracingCapable());
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
        data.setAddonPack(buffer.readBoolean()); // added
        data.setRayTracingCapable(buffer.readBoolean());
        return data;
    }
}