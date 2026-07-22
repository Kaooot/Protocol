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
import org.cloudburstmc.protocol.bedrock.data.SubChunkPos;
import org.cloudburstmc.protocol.bedrock.data.SubChunkPosOffset;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SubChunkRequestPacket implements BedrockPacket {
  private DimensionType dimensionType;

  private final List<SubChunkPosOffset> subChunkPositionOffsetList = new ObjectArrayList<>();

  private SubChunkPos centerPos;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SUB_CHUNK_REQUEST;
  }

  @Override
  public SubChunkRequestPacket clone() {
    try {
      return (SubChunkRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
