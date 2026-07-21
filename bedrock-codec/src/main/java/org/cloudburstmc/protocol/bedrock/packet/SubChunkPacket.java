package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.SubChunkPacketData;
import org.cloudburstmc.protocol.bedrock.data.SubChunkPos;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 174 (0xae)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SubChunkPacket implements BedrockPacket {
  private boolean cacheEnabled;

  private DimensionType dimensionType;

  private SubChunkPos centerPos;

  private final List<SubChunkPacketData> subChunkData = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SUB_CHUNK;
  }

  @Override
  public SubChunkPacket clone() {
    try {
      return (SubChunkPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
