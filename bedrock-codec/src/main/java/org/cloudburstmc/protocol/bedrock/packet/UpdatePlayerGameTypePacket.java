package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick;
import org.cloudburstmc.protocol.bedrock.data.world.GameType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class UpdatePlayerGameTypePacket implements BedrockPacket {

    private GameType playerGameType;
    private long targetPlayer;
    private PlayerInputTick tick = new PlayerInputTick(0L);

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UPDATE_PLAYER_GAME_TYPE;
    }

    @Override
    public UpdatePlayerGameTypePacket clone() {
        try {
            return (UpdatePlayerGameTypePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}