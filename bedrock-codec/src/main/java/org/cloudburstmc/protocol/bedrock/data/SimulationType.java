package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum SimulationType {
  GAME,

  EDITOR,

  TEST,

  INVALID;

  private static final SimulationType[] VALUES = values();

  public static SimulationType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SimulationType ID: " + ordinal);
  }
}
