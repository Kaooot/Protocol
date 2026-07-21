package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializedPersonaPieceHandle {
  private String pieceId;

  private PersonaPieceType pieceType;

  private UUID packId;

  private boolean isDefaultPiece;

  private String productId;
}
