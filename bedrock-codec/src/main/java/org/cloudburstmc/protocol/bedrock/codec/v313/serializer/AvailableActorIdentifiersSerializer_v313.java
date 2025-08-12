package org.cloudburstmc.protocol.bedrock.codec.v313.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.AvailableActorIdentifiersPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AvailableActorIdentifiersSerializer_v313 implements BedrockPacketSerializer<AvailableActorIdentifiersPacket> {
    public static final AvailableActorIdentifiersSerializer_v313 INSTANCE = new AvailableActorIdentifiersSerializer_v313();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AvailableActorIdentifiersPacket packet) {
        helper.writeTag(buffer, packet.getActorInfoList());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AvailableActorIdentifiersPacket packet) {
        packet.setActorInfoList(helper.readTag(buffer, NbtMap.class));
    }
}
