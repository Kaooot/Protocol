package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum AimAssistTargetMode {
  /**
   * angle
   */
  ANGLE,

  /**
   * distance
   */
  DISTANCE;

  private static final AimAssistTargetMode[] VALUES = values();

  public static AimAssistTargetMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AimAssistTargetMode ID: " + ordinal);
  }
}
