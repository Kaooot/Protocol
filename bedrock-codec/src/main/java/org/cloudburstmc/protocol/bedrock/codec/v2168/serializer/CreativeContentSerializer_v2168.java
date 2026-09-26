package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.CreativeContentSerializer_v776;
import org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeCategory;
import org.cloudburstmc.protocol.bedrock.data.item.creative.CreativeGroupInfoPayload;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreativeContentSerializer_v2168 extends CreativeContentSerializer_v776 {

    public static final CreativeContentSerializer_v2168 INSTANCE = new CreativeContentSerializer_v2168();

    @Override
    protected void writeCreativeGroupInfoPayload(ByteBuf buffer, BedrockCodecHelper helper, CreativeGroupInfoPayload payload) {
        buffer.writeByte(payload.getCreativeCategory().ordinal());
        helper.writeString(buffer, payload.getName());
        helper.writeItemInstance(buffer, payload.getGroupIconItem());
    }

    @Override
    protected CreativeGroupInfoPayload readCreativeGroupInfoPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final CreativeGroupInfoPayload payload = new CreativeGroupInfoPayload();
        payload.setCreativeCategory(CreativeCategory.from(buffer.readUnsignedByte()));
        payload.setName(helper.readString(buffer));
        payload.setGroupIconItem(helper.readItemInstance(buffer));
        return payload;
    }
}
