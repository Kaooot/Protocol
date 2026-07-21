package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.PlayStatus;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 2 (0x2)
 * Used after the Server handles a Login or (Sub)Client Authentication Packet
 * If everything is good, then it sends this packet to the client to finish the handshake.
 * If everything is not good, it terminates the connection.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayStatusPacket implements BedrockPacket {
  private PlayStatus status;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.PLAY_STATUS;
  }

  @Override
  public PlayStatusPacket clone() {
    try {
      return (PlayStatusPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
