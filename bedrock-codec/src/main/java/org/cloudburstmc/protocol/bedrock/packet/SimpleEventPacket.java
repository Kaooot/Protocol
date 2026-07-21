package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.Subtype;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 64 (0x40)
 * This is fired from the client to the server and a SetCommandsEnabledPacket is sent back when enabling commands.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SimpleEventPacket implements BedrockPacket {
  private Subtype type;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SIMPLE_EVENT;
  }

  @Override
  public SimpleEventPacket clone() {
    try {
      return (SimpleEventPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
