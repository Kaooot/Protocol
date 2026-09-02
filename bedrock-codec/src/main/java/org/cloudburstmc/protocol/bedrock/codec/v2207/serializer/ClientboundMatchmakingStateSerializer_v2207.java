package org.cloudburstmc.protocol.bedrock.codec.v2207.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.matchmaking.MatchmakingState;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMatchmakingStatePacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundMatchmakingStateSerializer_v2207 implements BedrockPacketSerializer<ClientboundMatchmakingStatePacket> {
    public static final ClientboundMatchmakingStateSerializer_v2207 INSTANCE = new ClientboundMatchmakingStateSerializer_v2207();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMatchmakingStatePacket packet) {
        buffer.writeByte(packet.getState().ordinal());
        helper.writeString(buffer, packet.getDestinationName());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMatchmakingStatePacket packet) {
        packet.setState(MatchmakingState.from(buffer.readUnsignedByte()));
        packet.setDestinationName(helper.readStringMaxLen(buffer, 100));
    }
}
