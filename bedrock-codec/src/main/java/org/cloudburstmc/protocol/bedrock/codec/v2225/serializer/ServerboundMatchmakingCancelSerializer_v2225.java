package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundMatchmakingCancelPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundMatchmakingCancelSerializer_v2225 implements BedrockPacketSerializer<ServerboundMatchmakingCancelPacket> {
    public static final ServerboundMatchmakingCancelSerializer_v2225 INSTANCE = new ServerboundMatchmakingCancelSerializer_v2225();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundMatchmakingCancelPacket packet) {
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundMatchmakingCancelPacket packet) {
    }
}