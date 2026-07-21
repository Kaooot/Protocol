package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum InputMode {
  UNDEFINED,

  MOUSE,

  TOUCH,

  GAME_PAD,

  COUNT;

  private static final InputMode[] VALUES = values();

  public static InputMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InputMode ID: " + ordinal);
  }
}
