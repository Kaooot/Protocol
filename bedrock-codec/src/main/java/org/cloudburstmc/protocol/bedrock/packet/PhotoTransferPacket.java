package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.PhotoType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 99 (0x63)
 * When the player uses the camera item or adds a photo to the scrapbook it sends the photo to the server,
 * then the server sends a response back on whether that was successful or not.
 * Either uploads a photo to the server's photoStorage or request one from it to be stored in client's photoStorage.
 * If no mPhotoData is provided it is a request for the given filename.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PhotoTransferPacket implements BedrockPacket {
  private String PhotoName;

  private String PhotoData;

  private String BookID;

  private PhotoType Type;

  private PhotoType SourceType;

  private long OwnerID;

  private String NewPhotoName;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.PHOTO_TRANSFER;
  }

  @Override
  public PhotoTransferPacket clone() {
    try {
      return (PhotoTransferPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
