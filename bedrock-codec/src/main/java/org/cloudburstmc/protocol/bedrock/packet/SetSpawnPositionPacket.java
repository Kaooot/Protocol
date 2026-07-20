package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.SpawnPositionType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 43 (0x2b)
 * see RespawnPacket
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetSpawnPositionPacket implements BedrockPacket {
  private SpawnPositionType spawnPositionType;

  private Vector3i blockPosition;

  private DimensionType dimensionType;

  private Vector3i spawnBlockPos;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_SPAWN_POSITION;
  }

  @Override
  public SetSpawnPositionPacket clone() {
    try {
      return (SetSpawnPositionPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
