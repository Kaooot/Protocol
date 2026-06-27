package org.cloudburstmc.protocol.bedrock.data.payload.skin;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class SerializedPersonaPieceHandle {

    private String pieceId;
    private PieceType pieceType;
    private UUID packId;
    private boolean isDefaultPiece;
    private String productId;
}