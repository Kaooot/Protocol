package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ClientPlayMode {
  /**
   * Normal
   */
  NORMAL,

  /**
   * Teaser
   */
  TEASER,

  /**
   * Screen
   */
  SCREEN,

  /**
   * ExitLevel
   */
  EXIT_LEVEL,

  /**
   * NumModes
   */
  NUM_MODES;

  private static final ClientPlayMode[] VALUES = values();

  public static ClientPlayMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ClientPlayMode ID: " + ordinal);
  }
}
