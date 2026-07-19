package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Float;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.PlayerInputTick;
import org.cloudburstmc.protocol.bedrock.data.RewindType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 161 (0xa1)
 * Used only in server authoritative movement mode, see ServerAuthMovementMode documentation.<br>
 * Since it is sent to the specified client the target player is implied to be the receiver.<br>
 * It is an optional part of the server authoritative protocol. A server could choose to never send this or do all corrections
 * through MovePlayerPacket, although doing so would likely provide less smooth results.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CorrectPlayerMovePredictionPacket implements BedrockPacket {
  private RewindType PredictionType;

  private Vector3f Pos;

  private Vector3f PosDelta;

  private Vector2f Rotation;

  private Float VehicleAngularVelocity;

  private boolean OnGround;

  private PlayerInputTick Tick;

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
