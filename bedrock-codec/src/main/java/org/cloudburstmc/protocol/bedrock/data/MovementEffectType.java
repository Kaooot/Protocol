package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum MovementEffectType {
  GLIDE_BOOST,

  DOLPHIN_BOOST,

  GEYSER_BOOST;

  private static final MovementEffectType[] VALUES = values();

  public static MovementEffectType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MovementEffectType ID: " + ordinal);
  }
}
