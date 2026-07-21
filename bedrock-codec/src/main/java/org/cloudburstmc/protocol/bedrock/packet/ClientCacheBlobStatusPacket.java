package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Long;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 135 (0x87)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientCacheBlobStatusPacket implements BedrockPacket {
  private final List<Long> missingIds = new ObjectArrayList<>();

  private final List<Long> foundIds = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENT_CACHE_BLOB_STATUS;
  }

  @Override
  public ClientCacheBlobStatusPacket clone() {
    try {
      return (ClientCacheBlobStatusPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
