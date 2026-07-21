package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.PlayerRespawnState;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 45 (0x2d)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class RespawnPacket implements BedrockPacket {
  private Vector3f position;

  private PlayerRespawnState state;

  private long playerRuntimeId;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.RESPAWN;
  }

  @Override
  public RespawnPacket clone() {
    try {
      return (RespawnPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
