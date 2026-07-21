package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum StructureRedstoneSaveMode {
  SAVES_TO_MEMORY,

  SAVES_TO_DISK;

  private static final StructureRedstoneSaveMode[] VALUES = values();

  public static StructureRedstoneSaveMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown StructureRedstoneSaveMode ID: " + ordinal);
  }
}
