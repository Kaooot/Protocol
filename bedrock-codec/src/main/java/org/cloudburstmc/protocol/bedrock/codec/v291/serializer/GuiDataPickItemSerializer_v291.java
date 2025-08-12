package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.GuiDataPickItemPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuiDataPickItemSerializer_v291 implements BedrockPacketSerializer<GuiDataPickItemPacket> {
    public static final GuiDataPickItemSerializer_v291 INSTANCE = new GuiDataPickItemSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GuiDataPickItemPacket packet) {
        helper.writeString(buffer, packet.getItemName());
        helper.writeString(buffer, packet.getItemEffectName());
        buffer.writeIntLE(packet.getSlot());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GuiDataPickItemPacket packet) {
        packet.setItemName(helper.readString(buffer));
        packet.setItemEffectName(helper.readString(buffer));
        packet.setSlot(buffer.readIntLE());
    }
}
