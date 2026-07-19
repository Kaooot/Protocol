package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InputMode {
  /**
   * Undefined
   */
  UNDEFINED,

  /**
   * Mouse
   */
  MOUSE,

  /**
   * Touch
   */
  TOUCH,

  /**
   * GamePad
   */
  GAME_PAD,

  /**
   * Count
   */
  COUNT;

  private static final InputMode[] VALUES = values();

  public static InputMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InputMode ID: " + ordinal);
  }
}
