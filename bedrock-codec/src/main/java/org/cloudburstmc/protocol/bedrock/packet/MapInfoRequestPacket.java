package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ClientPixelsProxy;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 68 (0x44)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MapInfoRequestPacket implements BedrockPacket {
  private long mapUniqueID;

  private final List<ClientPixelsProxy> clientPixelsList = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MAP_INFO_REQUEST;
  }

  @Override
  public MapInfoRequestPacket clone() {
    try {
      return (MapInfoRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
