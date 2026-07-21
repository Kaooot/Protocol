package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.MoveActorDeltaData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 111 (0x6f)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MoveActorDeltaPacket implements BedrockPacket {
  private MoveActorDeltaData moveData;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MOVE_ACTOR_DELTA;
  }

  @Override
  public MoveActorDeltaPacket clone() {
    try {
      return (MoveActorDeltaPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
