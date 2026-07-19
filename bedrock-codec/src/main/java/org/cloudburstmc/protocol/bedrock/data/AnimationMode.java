package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum AnimationMode {
  /**
   * None
   */
  NONE,

  /**
   * Layers
   */
  LAYERS,

  /**
   * Blocks
   */
  BLOCKS;

  private static final AnimationMode[] VALUES = values();

  public static AnimationMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AnimationMode ID: " + ordinal);
  }
}
