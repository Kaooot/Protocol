package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum StructureBlockType {
  DATA,

  SAVE,

  LOAD,

  CORNER,

  INVALID,

  EXPORT;

  private static final StructureBlockType[] VALUES = values();

  public static StructureBlockType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown StructureBlockType ID: " + ordinal);
  }
}
