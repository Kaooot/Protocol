package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 66 (0x42)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SpawnExperienceOrbPacket implements BedrockPacket {
  private Vector3f position;

  private int xpValue;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SPAWN_EXPERIENCE_ORB;
  }

  @Override
  public SpawnExperienceOrbPacket clone() {
    try {
      return (SpawnExperienceOrbPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
