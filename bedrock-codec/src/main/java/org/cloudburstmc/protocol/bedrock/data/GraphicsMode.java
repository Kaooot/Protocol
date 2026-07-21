package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum GraphicsMode {
  SIMPLE,

  FANCY,

  ADVANCED,

  RAY_TRACED;

  private static final GraphicsMode[] VALUES = values();

  public static GraphicsMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown GraphicsMode ID: " + ordinal);
  }
}
