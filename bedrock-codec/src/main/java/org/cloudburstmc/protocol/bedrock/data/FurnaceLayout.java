package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum FurnaceLayout {
  NONE,

  INVENTORY_ONLY,

  DEFAULT;

  private static final FurnaceLayout[] VALUES = values();

  public static FurnaceLayout from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown FurnaceLayout ID: " + ordinal);
  }
}
