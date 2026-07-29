package org.cloudburstmc.protocol.bedrock.data.furnace;

public enum FurnaceType {
  NONE,

  FURNACE,

  BLAST_FURNACE,

  SMOKER;

  private static final FurnaceType[] VALUES = values();

  public static FurnaceType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown FurnaceType ID: " + ordinal);
  }
}
