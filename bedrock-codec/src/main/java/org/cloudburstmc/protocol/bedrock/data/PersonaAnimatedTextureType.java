package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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
