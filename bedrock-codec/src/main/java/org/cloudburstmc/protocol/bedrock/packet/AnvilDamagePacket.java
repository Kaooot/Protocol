package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 141 (0x8d)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AnvilDamagePacket implements BedrockPacket {
  private Vector3i blockPosition;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ANVIL_DAMAGE;
  }

  @Override
  public AnvilDamagePacket clone() {
    try {
      return (AnvilDamagePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
