package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum HeightMapDataType {
  NO_DATA,

  HAS_DATA,

  ALL_TOO_HIGH,

  ALL_TOO_LOW,

  ALL_COPIED;

  private static final HeightMapDataType[] VALUES = values();

  public static HeightMapDataType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown HeightMapDataType ID: " + ordinal);
  }
}
