package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ClientPlayMode {
  NORMAL,

  TEASER,

  SCREEN,

  EXIT_LEVEL,

  NUM_MODES;

  private static final ClientPlayMode[] VALUES = values();

  public static ClientPlayMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ClientPlayMode ID: " + ordinal);
  }
}
