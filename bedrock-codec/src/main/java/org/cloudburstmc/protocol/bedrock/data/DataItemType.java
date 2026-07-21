package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum DataItemType {
  BYTE,

  SHORT,

  INT,

  FLOAT,

  STRING,

  COMPOUND_TAG,

  POS,

  INT64,

  VEC3;

  private static final DataItemType[] VALUES = values();

  public static DataItemType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown DataItemType ID: " + ordinal);
  }
}
