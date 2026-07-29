package org.cloudburstmc.protocol.bedrock.data.structure;

import java.lang.UnsupportedOperationException;

public enum AnimationMode {
  NONE,

  LAYERS,

  BLOCKS;

  private static final AnimationMode[] VALUES = values();

  public static AnimationMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AnimationMode ID: " + ordinal);
  }
}
