package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum Rotation {
  /**
   * None
   */
  NONE,

  /**
   * Rotate90
   */
  ROTATE90,

  /**
   * Rotate180
   */
  ROTATE180,

  /**
   * Rotate270
   */
  ROTATE270,

  /**
   * Clockwise90
   */
  CLOCKWISE90,

  /**
   * Clockwise180
   */
  CLOCKWISE180,

  /**
   * CounterClockwise90
   */
  COUNTER_CLOCKWISE90;

  private static final Rotation[] VALUES = values();

  public static Rotation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown Rotation ID: " + ordinal);
  }
}
