package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
