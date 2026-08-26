package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum HandSlot {
  MAINHAND,

  OFFHAND;

  private static final HandSlot[] VALUES = values();

  public static HandSlot from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown HandSlot ID: " + ordinal);
  }
}
