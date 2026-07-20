package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
