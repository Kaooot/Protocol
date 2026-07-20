package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
