package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.MissingBlobData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 136 (0x88)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientCacheMissResponsePacket implements BedrockPacket {
  private final List<MissingBlobData> missingBlobs = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENT_CACHE_MISS_RESPONSE;
  }

  @Override
  public ClientCacheMissResponsePacket clone() {
    try {
      return (ClientCacheMissResponsePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
