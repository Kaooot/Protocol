package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PersonaAnimatedTextureType {
  FACE,

  BODY32X32,

  BODY128X128;

  private static final PersonaAnimatedTextureType[] VALUES = values();

  public static PersonaAnimatedTextureType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PersonaAnimatedTextureType ID: " + ordinal);
  }
}
