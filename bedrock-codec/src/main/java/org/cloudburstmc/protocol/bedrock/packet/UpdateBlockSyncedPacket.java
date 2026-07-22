package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class UpdateBlockSyncedPacket implements BedrockPacket {
  private Vector3i blockPosition;

  private int blockRuntimeID;

  private int flags;

  private int layer;

  private long uniqueActorId;

  private long actorSyncMessage;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.UPDATE_BLOCK_SYNCED;
  }

  @Override
  public UpdateBlockSyncedPacket clone() {
    try {
      return (UpdateBlockSyncedPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
