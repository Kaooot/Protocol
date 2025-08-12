package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.AnvilDamagePacket;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AnvilDamageSerializer_v388 implements BedrockPacketSerializer<AnvilDamagePacket> {

    public static final AnvilDamageSerializer_v388 INSTANCE = new AnvilDamageSerializer_v388();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AnvilDamagePacket packet) {
        buffer.writeByte(packet.getDamageAmount());
        helper.writeBlockPosition(buffer, packet.getBlockPosition());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AnvilDamagePacket packet) {
        packet.setDamageAmount(buffer.readByte());
        packet.setBlockPosition(helper.readBlockPosition(buffer));
    }
}
