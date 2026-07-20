package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum Difficulty {
  PEACEFUL,

  EASY,

  NORMAL,

  HARD,

  COUNT,

  UNKNOWN;

  private static final Difficulty[] VALUES = values();

  public static Difficulty from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown Difficulty ID: " + ordinal);
  }
}
