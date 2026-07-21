package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.WebSocketPacketData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 95 (0x5f)
 * Only used though command to connect to server URLs. This is primarily used by EDU for connecting to their companion apps and other external applications through web sockets. Some mods/3rd party packs use it as well.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AutomationClientConnectPacket implements BedrockPacket {
  private WebSocketPacketData webSocketData;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.AUTOMATION_CLIENT_CONNECT;
  }

  @Override
  public AutomationClientConnectPacket clone() {
    try {
      return (AutomationClientConnectPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
