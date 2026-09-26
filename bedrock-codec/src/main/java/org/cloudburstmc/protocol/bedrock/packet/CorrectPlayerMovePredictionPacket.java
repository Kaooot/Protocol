package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.player.input.RewindType;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CorrectPlayerMovePredictionPacket implements BedrockPacket {

    private RewindType predictionType;
    private Vector3f pos;
    private Vector3f posDelta;
    private Vector2f rotation;
    private Float vehicleAngularVelocity;
    private boolean onGround;
    private PlayerInputTick tick = new PlayerInputTick(0L);

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CORRECT_PLAYER_MOVE_PREDICTION;
    }

    @Override
    public CorrectPlayerMovePredictionPacket clone() {
        try {
            return (CorrectPlayerMovePredictionPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}