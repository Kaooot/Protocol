package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.DebugMarkerData;
import org.cloudburstmc.protocol.bedrock.data.PayloadType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientboundDebugRendererPacket implements BedrockPacket {
  private PayloadType type;

  private DebugMarkerData debugMarkerData;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENTBOUND_DEBUG_RENDERER;
  }

  @Override
  public ClientboundDebugRendererPacket clone() {
    try {
      return (ClientboundDebugRendererPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
