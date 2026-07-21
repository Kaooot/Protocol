package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum RewindType {
  PLAYER,

  VEHICLE;

  private static final RewindType[] VALUES = values();

  public static RewindType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown RewindType ID: " + ordinal);
  }
}
