package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum BossBarColor {
  PINK,

  BLUE,

  RED,

  GREEN,

  YELLOW,

  PURPLE,

  REBECCA_PURPLE,

  WHITE;

  private static final BossBarColor[] VALUES = values();

  public static BossBarColor from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown BossBarColor ID: " + ordinal);
  }
}
