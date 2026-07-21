package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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
