package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CodeBuilderStorageQueryOptionsOperation {
  NONE,

  GET,

  SET,

  RESET;

  private static final CodeBuilderStorageQueryOptionsOperation[] VALUES = values();

  public static CodeBuilderStorageQueryOptionsOperation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CodeBuilderStorageQueryOptionsOperation ID: " + ordinal);
  }
}
