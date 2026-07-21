package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.ChunkPos;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 121 (0x79)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class NetworkChunkPublisherUpdatePacket implements BedrockPacket {
  private Vector3i newPositionForView;

  private int newRadiusForView;

  private final List<ChunkPos> serverBuiltChunksList = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.NETWORK_CHUNK_PUBLISHER_UPDATE;
  }

  @Override
  public NetworkChunkPublisherUpdatePacket clone() {
    try {
      return (NetworkChunkPublisherUpdatePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
