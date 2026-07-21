package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.MovementEffectType;
import org.cloudburstmc.protocol.bedrock.data.PlayerInputTick;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 318 (0x13e)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MovementEffectPacket implements BedrockPacket {
  private long targetRuntimeID;

  private MovementEffectType effectID;

  private int effectDuration;

  private PlayerInputTick tick;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MOVEMENT_EFFECT;
  }

  @Override
  public MovementEffectPacket clone() {
    try {
      return (MovementEffectPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
