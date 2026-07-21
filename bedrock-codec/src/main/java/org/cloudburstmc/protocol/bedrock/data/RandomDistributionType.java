package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum RandomDistributionType {
  SINGLE_VALUED,

  UNIFORM,

  GAUSSIAN,

  INVERSE_GAUSSIAN,

  FIXED_GRID,

  JITTERED_GRID,

  TRIANGLE;

  private static final RandomDistributionType[] VALUES = values();

  public static RandomDistributionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown RandomDistributionType ID: " + ordinal);
  }
}
