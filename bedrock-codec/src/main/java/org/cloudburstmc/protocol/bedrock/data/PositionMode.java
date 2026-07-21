package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PositionMode {
  NORMAL,

  RESPAWN,

  TELEPORT,

  ONLY_HEAD_ROT;

  private static final PositionMode[] VALUES = values();

  public static PositionMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PositionMode ID: " + ordinal);
  }
}
