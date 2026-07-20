package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ItemVersion {
  LEGACY,

  DATA_DRIVEN,

  NONE;

  private static final ItemVersion[] VALUES = values();

  public static ItemVersion from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemVersion ID: " + ordinal);
  }
}
