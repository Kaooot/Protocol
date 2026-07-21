package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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
