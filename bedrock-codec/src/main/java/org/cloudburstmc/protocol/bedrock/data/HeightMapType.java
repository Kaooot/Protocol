package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum HeightMapType {
  NO_DATA,

  HAS_DATA,

  ALL_TOO_HIGH,

  ALL_TOO_LOW;

  private static final HeightMapType[] VALUES = values();

  public static HeightMapType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown HeightMapType ID: " + ordinal);
  }
}
