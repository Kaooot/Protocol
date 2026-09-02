package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.payload.matchmaking.MatchmakingState;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 * @since v2207
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundMatchmakingStatePacket implements BedrockPacket {

    private MatchmakingState state;
    private String destinationName;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_MATCHMAKING_STATE;
    }

    @Override
    public ClientboundMatchmakingStatePacket clone() {
        try {
            return (ClientboundMatchmakingStatePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
