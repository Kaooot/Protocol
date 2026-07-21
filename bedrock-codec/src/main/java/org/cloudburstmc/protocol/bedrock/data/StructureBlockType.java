package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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
