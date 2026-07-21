package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.UUID;
import lombok.Data;

@Data
public class SerializedPersonaPieceHandle {
  private String pieceId;

  private PersonaPieceType pieceType;

  private UUID packId;

  private boolean isDefaultPiece;

  private String productId;
}
