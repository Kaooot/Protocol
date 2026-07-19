package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum personaAnimatedTextureType {
  /**
   * Face
   */
  FACE,

  /**
   * Body32x32
   */
  BODY32X32,

  /**
   * Body128x128
   */
  BODY128X128;

  private static final personaAnimatedTextureType[] VALUES = values();

  public static personaAnimatedTextureType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown personaAnimatedTextureType ID: " + ordinal);
  }
}
