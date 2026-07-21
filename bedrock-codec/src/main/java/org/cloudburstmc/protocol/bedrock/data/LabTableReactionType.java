package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum LabTableReactionType {
  NONE,

  ICE_BOMB,

  BLEACH,

  ELEPHANT_TOOTHPASTE,

  FERTILIZER,

  HEAT_BLOCK,

  MAGNESIUM_SALTS,

  MISC_FIRE,

  MISC_EXPLOSION,

  MISC_LAVA,

  MISC_MYSTICAL,

  MISC_SMOKE,

  MISC_LARGE_SMOKE;

  private static final LabTableReactionType[] VALUES = values();

  public static LabTableReactionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown LabTableReactionType ID: " + ordinal);
  }
}
