package org.cloudburstmc.protocol.bedrock.data.payload.skin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum PieceType {

    UNKNOWN("unknown", "persona_unknown"),
    SKELETON("skeleton", "persona_skeleton"),
    BODY("body", "persona_body"),
    SKIN("skin", "persona_skin"),
    BOTTOM("bottom", "persona_bottom"),
    FEET("feet", "persona_feet"),
    DRESS("dress", "persona_dress"),
    TOP("top", "persona_top"),
    HIGH_PANTS("high_pants", "persona_high_pants"),
    HANDS("hands", "persona_hand"),
    OUTERWEAR("outerwear", "persona_outerwear"),
    FACIAL_HAIR("facialhair", "persona_facial_hair"),
    MOUTH("mouth", "persona_mouth"),
    EYES("eyes", "persona_eyes"),
    HAIR("hair", "persona_hair"),
    HOOD("hood", "persona_hood"),
    BACK("back", "persona_back"),
    FACE_ACCESSORY("faceaccessory", "persona_face_accessory"),
    HEAD("head", "persona_head"),
    LEGS("legs", "persona_legs"),
    LEFT_LEG("leftleg", "persona_left_leg"),
    RIGHT_LEG("rightleg", "persona_right_leg"),
    ARMS("arms", "persona_arms"),
    LEFT_ARM("leftarm", "persona_left_arm"),
    RIGHT_ARM("rightarm", "persona_right_arm"),
    CAPES("capes", "persona_capes"),
    CLASSIC_SKIN("classicskin", "persona_classic_skin"),
    EMOTE("emote", "persona_emote"),
    UNSUPPORTED("unsupported", "unsupported");

    private static final PieceType[] VALUES = values();

    private final String id;
    private final String personaId;

    public static PieceType from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        return UNKNOWN;
    }

    public static PieceType from(String value) {
        for (PieceType pieceType : VALUES) {
            if (pieceType.matches(value)) {
                return pieceType;
            }
        }
        return UNKNOWN;
    }

    private boolean matches(String value) {
        // clients may send the piece type prefixed and/or with underscores, e.g. persona_facial_hair
        final String normalized = value.replace("_", "");
        return normalized.equalsIgnoreCase(this.id.replace("_", "")) ||
                normalized.equalsIgnoreCase(this.personaId.replace("_", ""));
    }
}