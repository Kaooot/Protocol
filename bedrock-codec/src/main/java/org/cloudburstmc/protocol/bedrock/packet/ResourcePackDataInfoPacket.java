package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 82 (0x52)
 * Sent from the serverFileChunkUploader during the initialization of the file uploader. This packet is sent to the primary client.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ResourcePackDataInfoPacket implements BedrockPacket {
  private String resourceName;

  private int chunkSize;

  private int numberOfChunks;

  private long fileSize;

  private String fileHash;

  private boolean isPremiumPack;

  private int packType;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.RESOURCE_PACK_DATA_INFO;
  }

  @Override
  public ResourcePackDataInfoPacket clone() {
    try {
      return (ResourcePackDataInfoPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
