package org.cloudburstmc.protocol.bedrock.data.payload.skin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum PieceType {

    SKELETON("skeleton"),
    BODY("body"),
    SKIN("skin"),
    BOTTOM("bottom"),
    FEET("feet"),
    DRESS("dress"),
    TOP("top"),
    HIGH_PANTS("high_pants"),
    HANDS("hands"),
    OUTERWEAR("outerwear"),
    FACIAL_HAIR("facialhair"),
    MOUTH("mouth"),
    EYES("eyes"),
    HAIR("hair"),
    HOOD("hood"),
    BACK("back"),
    FACE_ACCESSORY("faceaccessory"),
    HEAD("head"),
    LEGS("legs"),
    LEFT_LEG("leftleg"),
    RIGHT_LEG("rightleg"),
    ARMS("arms"),
    LEFT_ARM("leftarm"),
    RIGHT_ARM("rightarm"),
    CAPES("capes"),
    CLASSIC_SKIN("classicskin"),
    EMOTE("emote");

    private static final PieceType[] VALUES = values();

    private final String id;

    public static PieceType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown persona::PieceType ID: " + ordinal);
    }

    public static PieceType from(String value) {
        for (PieceType pieceType : VALUES) {
            if (pieceType.getId().equalsIgnoreCase(value)) {
                return pieceType;
            }
        }
        throw new UnsupportedOperationException("Detected unknown persona::PieceType ID: " + value);
    }
}