package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.lang.UnsupportedOperationException;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonaPieceType {
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

  private static final PersonaPieceType[] VALUES = values();

  private static final Map<String, PersonaPieceType> SERIALIZE_NAMES = new HashMap<>(values().length);

  static {
    for (PersonaPieceType value : values()) {
      SERIALIZE_NAMES.put(value.getSerializeName(), value);
    }
  }

  private final String serializeName;

  public static PersonaPieceType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PersonaPieceType ID: " + ordinal);
  }

  public static PersonaPieceType fromName(String serializeName) {
    return SERIALIZE_NAMES.get(serializeName);
  }
}
