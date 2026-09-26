package org.cloudburstmc.protocol.bedrock.codec.v534.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.text.DeathCauseMessageType;
import org.cloudburstmc.protocol.bedrock.packet.DeathInfoPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeathInfoSerializer_v534 implements BedrockPacketSerializer<DeathInfoPacket> {
    public static final DeathInfoSerializer_v534 INSTANCE = new DeathInfoSerializer_v534();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DeathInfoPacket packet) {
        helper.writeString(buffer, packet.getDeathCauseMessage().getDeathCauseAttackName());
        helper.writeArray(buffer, packet.getDeathCauseMessage().getDeathCauseMessageList(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DeathInfoPacket packet) {
        final DeathCauseMessageType deathCauseMessage = new DeathCauseMessageType();
        deathCauseMessage.setDeathCauseAttackName(helper.readString(buffer));
        helper.readArray(buffer, deathCauseMessage.getDeathCauseMessageList(), helper::readString);
        packet.setDeathCauseMessage(deathCauseMessage);
    }
}