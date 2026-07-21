package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CodeBuilderStorageQueryOptionsCategory {
  NONE,

  CODE_STATUS,

  INSTANTIATION;

  private static final CodeBuilderStorageQueryOptionsCategory[] VALUES = values();

  public static CodeBuilderStorageQueryOptionsCategory from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CodeBuilderStorageQueryOptionsCategory ID: " + ordinal);
  }
}
