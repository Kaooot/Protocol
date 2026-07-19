package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum personaArmSizeType {
  /**
   * Slim
   */
  SLIM,

  /**
   * Wide
   */
  WIDE;

  private static final personaArmSizeType[] VALUES = values();

  public static personaArmSizeType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown personaArmSizeType ID: " + ordinal);
  }
}
