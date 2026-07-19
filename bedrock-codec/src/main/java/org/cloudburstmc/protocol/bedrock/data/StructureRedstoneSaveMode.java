package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum StructureRedstoneSaveMode {
  /**
   * SavesToMemory
   */
  SAVES_TO_MEMORY,

  /**
   * SavesToDisk
   */
  SAVES_TO_DISK;

  private static final StructureRedstoneSaveMode[] VALUES = values();

  public static StructureRedstoneSaveMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown StructureRedstoneSaveMode ID: " + ordinal);
  }
}
