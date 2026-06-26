package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.CreativeContentSerializer_v776;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeGroupInfoPayload;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeItemCategory;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeItemEntryPayload;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreativeContentSerializer_v2164 extends CreativeContentSerializer_v776 {
    public static final CreativeContentSerializer_v2164 INSTANCE = new CreativeContentSerializer_v2164();

    @Override
    protected void writeCreativeGroupInfoPayload(ByteBuf buffer, BedrockCodecHelper helper, CreativeGroupInfoPayload payload) {
        buffer.writeByte(payload.getCreativeCategory().ordinal());
        helper.writeString(buffer, payload.getName());
        helper.writeNetworkItemInstanceDescriptor(buffer, payload.getGroupIconItem());
    }

    @Override
    protected CreativeGroupInfoPayload readCreativeGroupInfoPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final CreativeGroupInfoPayload payload = new CreativeGroupInfoPayload();
        payload.setCreativeCategory(CreativeItemCategory.from(buffer.readUnsignedByte()));
        payload.setName(helper.readString(buffer));
        payload.setGroupIconItem(helper.readNetworkItemInstanceDescriptor(buffer));
        return payload;
    }

    @Override
    protected void writeCreativeItemEntryPayload(ByteBuf buffer, BedrockCodecHelper helper, CreativeItemEntryPayload payload) {
        this.writeCreativeItemNetId(buffer, helper, payload.getCreativeNetId());
        helper.writeNetworkItemInstanceDescriptor(buffer, payload.getItemInstance());
        VarInts.writeUnsignedInt(buffer, payload.getGroupIndex());
    }

    @Override
    protected CreativeItemEntryPayload readCreativeItemEntryPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final CreativeItemEntryPayload payload = new CreativeItemEntryPayload();
        payload.setCreativeNetId(this.readCreativeItemNetId(buffer, helper));
        payload.setItemInstance(helper.readNetworkItemInstanceDescriptor(buffer));
        payload.setGroupIndex(VarInts.readUnsignedInt(buffer));
        return payload;
    }
}