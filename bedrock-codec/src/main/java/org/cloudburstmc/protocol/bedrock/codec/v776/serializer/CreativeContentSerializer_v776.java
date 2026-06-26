package org.cloudburstmc.protocol.bedrock.codec.v776.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.CreativeContentSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeGroupInfoPayload;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeItemCategory;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeItemEntryPayload;
import org.cloudburstmc.protocol.bedrock.packet.CreativeContentPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreativeContentSerializer_v776 extends CreativeContentSerializer_v407 {
    public static final CreativeContentSerializer_v776 INSTANCE = new CreativeContentSerializer_v776();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CreativeContentPacket packet) {
        helper.writeArray(buffer, packet.getGroups(), this::writeCreativeGroupInfoPayload);
        helper.writeArray(buffer, packet.getEntries(), this::writeCreativeItemEntryPayload);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CreativeContentPacket packet) {
        helper.readArray(buffer, packet.getGroups(), this::readCreativeGroupInfoPayload);
        helper.readArray(buffer, packet.getEntries(), this::readCreativeItemEntryPayload);
    }

    protected void writeCreativeGroupInfoPayload(ByteBuf buffer, BedrockCodecHelper helper, CreativeGroupInfoPayload payload) {
        buffer.writeIntLE(payload.getCreativeCategory().ordinal());
        helper.writeString(buffer, payload.getName());
        helper.writeItemInstance(buffer, payload.getGroupIconItem());
    }

    protected CreativeGroupInfoPayload readCreativeGroupInfoPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final CreativeGroupInfoPayload payload = new CreativeGroupInfoPayload();
        payload.setCreativeCategory(CreativeItemCategory.from(buffer.readIntLE()));
        payload.setName(helper.readString(buffer));
        payload.setGroupIconItem(helper.readItemInstance(buffer));
        return payload;
    }

    @Override
    protected void writeCreativeItemEntryPayload(ByteBuf buffer, BedrockCodecHelper helper, CreativeItemEntryPayload payload) {
        this.writeCreativeItemNetId(buffer, helper, payload.getCreativeNetId());
        helper.writeItemInstance(buffer, payload.getItemInstance());
        VarInts.writeUnsignedInt(buffer, payload.getGroupIndex());
    }

    @Override
    protected CreativeItemEntryPayload readCreativeItemEntryPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final CreativeItemEntryPayload payload = new CreativeItemEntryPayload();
        payload.setCreativeNetId(this.readCreativeItemNetId(buffer, helper));
        payload.setItemInstance(helper.readItemInstance(buffer));
        payload.setGroupIndex(VarInts.readUnsignedInt(buffer));
        return payload;
    }
}
