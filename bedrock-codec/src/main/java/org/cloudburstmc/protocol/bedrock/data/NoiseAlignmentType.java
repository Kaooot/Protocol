package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum NoiseAlignmentType {
  MIN_LOCAL_TRANSITION_END;

  private static final NoiseAlignmentType[] VALUES = values();

  public static NoiseAlignmentType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown NoiseAlignmentType ID: " + ordinal);
  }
}
