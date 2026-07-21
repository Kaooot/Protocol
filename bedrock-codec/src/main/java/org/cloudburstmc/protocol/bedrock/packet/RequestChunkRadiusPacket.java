package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 69 (0x45)
 * This packet is to make sure that the server expands/shrinks first. Additionally for ClientSide Chunk Generation we can send a byte, based on client's hardware capabilities what is the max chunk radius client can handle.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class RequestChunkRadiusPacket implements BedrockPacket {
  private int chunkRadius;

  private int maxChunkradius;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.REQUEST_CHUNK_RADIUS;
  }

  @Override
  public RequestChunkRadiusPacket clone() {
    try {
      return (RequestChunkRadiusPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
