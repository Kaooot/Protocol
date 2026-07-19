package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum CodeBuilderExecutionStateCodeStatus {
  /**
   * None
   */
  NONE,

  /**
   * NotStarted
   */
  NOT_STARTED,

  /**
   * InProgress
   */
  IN_PROGRESS,

  /**
   * Paused
   */
  PAUSED,

  /**
   * Error
   */
  ERROR,

  /**
   * Succeeded
   */
  SUCCEEDED;

  private static final CodeBuilderExecutionStateCodeStatus[] VALUES = values();

  public static CodeBuilderExecutionStateCodeStatus from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CodeBuilderExecutionStateCodeStatus ID: " + ordinal);
  }
}
