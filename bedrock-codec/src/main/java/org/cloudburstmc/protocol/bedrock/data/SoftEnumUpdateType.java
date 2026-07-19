package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum SoftEnumUpdateType {
  /**
   * Add
   */
  ADD,

  /**
   * Remove
   */
  REMOVE,

  /**
   * Replace
   */
  REPLACE;

  private static final SoftEnumUpdateType[] VALUES = values();

  public static SoftEnumUpdateType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SoftEnumUpdateType ID: " + ordinal);
  }
}
