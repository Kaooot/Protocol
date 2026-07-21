package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.LoadingScreenPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 312 (0x138)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ServerboundLoadingScreenPacket implements BedrockPacket {
  private LoadingScreenPacketType loadingScreenPacketType;

  private int loadingScreenId;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SERVERBOUND_LOADING_SCREEN;
  }

  @Override
  public ServerboundLoadingScreenPacket clone() {
    try {
      return (ServerboundLoadingScreenPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
