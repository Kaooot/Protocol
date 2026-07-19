package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum GraphicsMode {
  /**
   * Simple
   */
  SIMPLE,

  /**
   * Fancy
   */
  FANCY,

  /**
   * Advanced
   */
  ADVANCED,

  /**
   * RayTraced
   */
  RAY_TRACED;

  private static final GraphicsMode[] VALUES = values();

  public static GraphicsMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown GraphicsMode ID: " + ordinal);
  }
}
