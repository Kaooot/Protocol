package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum Mirror {
  NONE,

  X,

  Z,

  XZ;

  private static final Mirror[] VALUES = values();

  public static Mirror from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown Mirror ID: " + ordinal);
  }
}
