package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Object;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 344 (0x158)
 * Sent from the server when a client joins to initialize all world clocks for the client and periodically to all clients to keep them in sync.
 * It is also sent to all clients when a world clock's paused state changes or when time markers are added or removed.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SyncWorldClocksPacket implements BedrockPacket {
  private Object data;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SYNC_WORLD_CLOCKS;
  }

  @Override
  public SyncWorldClocksPacket clone() {
    try {
      return (SyncWorldClocksPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
