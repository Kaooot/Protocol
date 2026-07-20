package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.PlayerInputTick;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 40 (0x28)
 * It is primarily relevant for client predicted entities like the player or a boat or horse they are in control of.
 * For most other actor types it does nothing.<br>
 * This is one of the packets that can directly affect player motion, for others, see:<br>
 * - MovePlayerPacket<br>
 * - CorrectPlayerMovePredictionPacket
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetActorMotionPacket implements BedrockPacket {
  private long targetRuntimeID;

  private Vector3f motion;

  private PlayerInputTick tick;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_ACTOR_MOTION;
  }

  @Override
  public SetActorMotionPacket clone() {
    try {
      return (SetActorMotionPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
