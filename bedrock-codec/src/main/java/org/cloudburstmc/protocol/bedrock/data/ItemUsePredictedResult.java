package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ItemUsePredictedResult {
  FAILURE,

  SUCCESS;

  private static final ItemUsePredictedResult[] VALUES = values();

  public static ItemUsePredictedResult from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemUsePredictedResult ID: " + ordinal);
  }
}
