package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.connection.matchmaking.MatchmakingState;
import org.cloudburstmc.protocol.bedrock.data.connection.matchmaking.MatchmakingStateOptions;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMatchmakingStatePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundMatchmakingStateSerializer_v2225 implements BedrockPacketSerializer<ClientboundMatchmakingStatePacket> {
    public static final ClientboundMatchmakingStateSerializer_v2225 INSTANCE = new ClientboundMatchmakingStateSerializer_v2225();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMatchmakingStatePacket packet) {
        buffer.writeByte(packet.getState().ordinal());
        helper.writeString(buffer, packet.getDestinationName());
        helper.writeOptionalNull(buffer, packet.getOptions(), this::writeMatchmakingStateOptions);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMatchmakingStatePacket packet) {
        packet.setState(MatchmakingState.from(buffer.readUnsignedByte()));
        packet.setDestinationName(helper.readStringMaxLen(buffer, 100));
        packet.setOptions(helper.readOptional(buffer, null, this::readMatchmakingStateOptions));
    }

    protected void writeMatchmakingStateOptions(ByteBuf buffer, BedrockCodecHelper helper, MatchmakingStateOptions options) {
        helper.writeString(buffer, options.getTriggeringPlayerName());
        buffer.writeBoolean(options.isTriggeredByLocalPlayer());
    }

    protected MatchmakingStateOptions readMatchmakingStateOptions(ByteBuf buffer, BedrockCodecHelper helper) {
        final MatchmakingStateOptions options = new MatchmakingStateOptions();
        options.setTriggeringPlayerName(helper.readStringMaxLen(buffer, 40));
        options.setTriggeredByLocalPlayer(buffer.readBoolean());
        return options;
    }
}