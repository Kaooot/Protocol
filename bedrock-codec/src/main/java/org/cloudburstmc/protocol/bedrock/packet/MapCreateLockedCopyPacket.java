package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 131 (0x83)
 * It sends the original map id and the new map id. 
 * On the server it follows a similar process to creating a new map, sends the data and the map info to the client.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MapCreateLockedCopyPacket implements BedrockPacket {
  private long originalMapId;

  private long newMapId;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MAP_CREATE_LOCKED_COPY;
  }

  @Override
  public MapCreateLockedCopyPacket clone() {
    try {
      return (MapCreateLockedCopyPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
