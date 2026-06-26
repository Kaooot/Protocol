package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeItemEntryPayload;
import org.cloudburstmc.protocol.bedrock.data.payload.creative.CreativeItemNetId;
import org.cloudburstmc.protocol.bedrock.packet.CreativeContentPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreativeContentSerializer_v407 implements BedrockPacketSerializer<CreativeContentPacket> {

    public static final CreativeContentSerializer_v407 INSTANCE = new CreativeContentSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CreativeContentPacket packet) {
        helper.writeArray(buffer, packet.getEntries(), this::writeCreativeItemEntryPayload);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CreativeContentPacket packet) {
        helper.readArray(buffer, packet.getEntries(), this::readCreativeItemEntryPayload);
    }

    protected void writeCreativeItemNetId(ByteBuf buffer, BedrockCodecHelper helper, CreativeItemNetId netId) {
        VarInts.writeUnsignedInt(buffer, netId.getID());
    }

    protected CreativeItemNetId readCreativeItemNetId(ByteBuf buffer, BedrockCodecHelper helper) {
        return new CreativeItemNetId(VarInts.readUnsignedInt(buffer));
    }

    protected void writeCreativeItemEntryPayload(ByteBuf buffer, BedrockCodecHelper helper, CreativeItemEntryPayload payload) {
        this.writeCreativeItemNetId(buffer, helper, payload.getCreativeNetId());
        helper.writeItemInstance(buffer, payload.getItemInstance());
    }

    protected CreativeItemEntryPayload readCreativeItemEntryPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final CreativeItemEntryPayload payload = new CreativeItemEntryPayload();
        payload.setCreativeNetId(this.readCreativeItemNetId(buffer, helper));
        payload.setItemInstance(helper.readItemInstance(buffer));
        return payload;
    }
}
