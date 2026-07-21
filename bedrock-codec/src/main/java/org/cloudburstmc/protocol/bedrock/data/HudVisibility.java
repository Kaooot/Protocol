package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum HudVisibility {
  HIDE,

  RESET;

  private static final HudVisibility[] VALUES = values();

  public static HudVisibility from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown HudVisibility ID: " + ordinal);
  }
}
