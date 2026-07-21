package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.MoveActorAbsoluteData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 18 (0x12)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MoveActorAbsolutePacket implements BedrockPacket {
  private MoveActorAbsoluteData moveData;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MOVE_ACTOR_ABSOLUTE;
  }

  @Override
  public MoveActorAbsolutePacket clone() {
    try {
      return (MoveActorAbsolutePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
