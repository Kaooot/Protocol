package org.cloudburstmc.protocol.bedrock.codec.v2207.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundMatchmakingCancelPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundMatchmakingCancelSerializer_v2207 implements BedrockPacketSerializer<ServerboundMatchmakingCancelPacket> {
    public static final ServerboundMatchmakingCancelSerializer_v2207 INSTANCE = new ServerboundMatchmakingCancelSerializer_v2207();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundMatchmakingCancelPacket packet) {
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundMatchmakingCancelPacket packet) {
    }
}
