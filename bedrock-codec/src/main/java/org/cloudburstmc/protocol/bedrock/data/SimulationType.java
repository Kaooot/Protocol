package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum SimulationType {
  /**
   * Game
   */
  GAME,

  /**
   * Editor
   */
  EDITOR,

  /**
   * Test
   */
  TEST,

  INVALID;

  private static final SimulationType[] VALUES = values();

  public static SimulationType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SimulationType ID: " + ordinal);
  }
}
