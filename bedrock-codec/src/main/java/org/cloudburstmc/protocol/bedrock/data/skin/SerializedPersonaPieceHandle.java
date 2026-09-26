package org.cloudburstmc.protocol.bedrock.data.skin;

import lombok.Data;

import java.util.UUID;

@Data
public class SerializedPersonaPieceHandle {

    private String pieceId;
    private PieceType pieceType;
    private UUID packId;
    private boolean isDefaultPiece;
    private String productId;
}