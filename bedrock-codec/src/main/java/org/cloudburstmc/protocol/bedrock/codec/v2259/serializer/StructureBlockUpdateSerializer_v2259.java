package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.StructureBlockUpdateSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.packet.StructureBlockUpdatePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructureBlockUpdateSerializer_v2259 extends StructureBlockUpdateSerializer_v2168 {
    public static final StructureBlockUpdateSerializer_v2259 INSTANCE = new StructureBlockUpdateSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeBoolean(packet.isKeepStructureVoid());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setKeepStructureVoid(buffer.readBoolean());
    }
}