package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.GraphicsMode;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 323 (0x143)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class UpdateClientOptionsPacket implements BedrockPacket {
  private GraphicsMode graphicsModeChange;

  private boolean filterProfanityChange;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.UPDATE_CLIENT_OPTIONS;
  }

  @Override
  public UpdateClientOptionsPacket clone() {
    try {
      return (UpdateClientOptionsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
