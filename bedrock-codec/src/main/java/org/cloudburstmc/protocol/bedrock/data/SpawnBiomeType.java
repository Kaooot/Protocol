package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum SpawnBiomeType {
  DEFAULT,

  USER_DEFINED;

  private static final SpawnBiomeType[] VALUES = values();

  public static SpawnBiomeType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SpawnBiomeType ID: " + ordinal);
  }
}
