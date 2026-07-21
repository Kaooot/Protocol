package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CodeBuilderExecutionStateCodeStatus {
  NONE,

  NOT_STARTED,

  IN_PROGRESS,

  PAUSED,

  ERROR,

  SUCCEEDED;

  private static final CodeBuilderExecutionStateCodeStatus[] VALUES = values();

  public static CodeBuilderExecutionStateCodeStatus from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CodeBuilderExecutionStateCodeStatus ID: " + ordinal);
  }
}
