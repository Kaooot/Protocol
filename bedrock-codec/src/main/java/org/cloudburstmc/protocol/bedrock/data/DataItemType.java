package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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
