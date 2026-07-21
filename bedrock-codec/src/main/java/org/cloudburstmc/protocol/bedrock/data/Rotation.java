package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum Rotation {
  NONE,

  ROTATE90,

  ROTATE180,

  ROTATE270,

  CLOCKWISE90,

  CLOCKWISE180,

  COUNTER_CLOCKWISE90;

  private static final Rotation[] VALUES = values();

  public static Rotation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown Rotation ID: " + ordinal);
  }
}
