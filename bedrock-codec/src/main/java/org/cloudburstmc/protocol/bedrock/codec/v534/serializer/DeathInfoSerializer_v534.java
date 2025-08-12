package org.cloudburstmc.protocol.bedrock.codec.v534.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.DeathInfoPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeathInfoSerializer_v534 implements BedrockPacketSerializer<DeathInfoPacket> {
    public static final DeathInfoSerializer_v534 INSTANCE = new DeathInfoSerializer_v534();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DeathInfoPacket packet) {
        helper.writeString(buffer, packet.getDeathCauseAttackName());
        helper.writeArray(buffer, packet.getDeathCauseMessageList(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DeathInfoPacket packet) {
        packet.setDeathCauseAttackName(helper.readString(buffer));
        helper.readArray(buffer, packet.getDeathCauseMessageList(), helper::readString);
    }
}
