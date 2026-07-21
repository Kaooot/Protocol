package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum SoftEnumUpdateType {
  ADD,

  REMOVE,

  REPLACE;

  private static final SoftEnumUpdateType[] VALUES = values();

  public static SoftEnumUpdateType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SoftEnumUpdateType ID: " + ordinal);
  }
}
