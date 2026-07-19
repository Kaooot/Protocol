package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum personaAnimationExpression {
  /**
   * Linear
   */
  LINEAR,

  /**
   * Blinking
   */
  BLINKING;

  private static final personaAnimationExpression[] VALUES = values();

  public static personaAnimationExpression from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown personaAnimationExpression ID: " + ordinal);
  }
}
