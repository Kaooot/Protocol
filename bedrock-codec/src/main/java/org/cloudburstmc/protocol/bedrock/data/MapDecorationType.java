package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum MapDecorationType {
  MARKER_WHITE,

  MARKER_GREEN,

  MARKER_RED,

  MARKER_BLUE,

  XWHITE,

  TRIANGLE_RED,

  SQUARE_WHITE,

  MARKER_SIGN,

  MARKER_PINK,

  MARKER_ORANGE,

  MARKER_YELLOW,

  MARKER_TEAL,

  TRIANGLE_GREEN,

  SMALL_SQUARE_WHITE,

  MANSION,

  MONUMENT,

  NO_DRAW,

  VILLAGE_DESERT,

  VILLAGE_PLAINS,

  VILLAGE_SAVANNA,

  VILLAGE_SNOWY,

  VILLAGE_TAIGA,

  JUNGLE_TEMPLE,

  WITCH_HUT,

  TRIAL_CHAMBERS,

  COUNT;

  private static final MapDecorationType[] VALUES = values();

  public static MapDecorationType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MapDecorationType ID: " + ordinal);
  }
}
