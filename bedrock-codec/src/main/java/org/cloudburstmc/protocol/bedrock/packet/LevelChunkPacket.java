package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ChunkPos;
import org.cloudburstmc.protocol.bedrock.data.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.LevelChunkPacketPayloadSubChunkMetadata;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 58 (0x3a)
 * Used to start a chunk transaction.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class LevelChunkPacket implements BedrockPacket {
  private ChunkPos chunkPosition;

  private DimensionType dimensionId;

  private int subchunksCount;

  private Integer clientRequestSubchunkLimit;

  private boolean cacheEnabled;

  private final List<LevelChunkPacketPayloadSubChunkMetadata> cacheMetadata = new ObjectArrayList<>();

  private String serializedChunkData;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.LEVEL_CHUNK;
  }

  @Override
  public LevelChunkPacket clone() {
    try {
      return (LevelChunkPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
