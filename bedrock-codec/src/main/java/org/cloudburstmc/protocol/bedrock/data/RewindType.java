package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum RewindType {
  /**
   * Player
   */
  PLAYER,

  /**
   * Vehicle
   */
  VEHICLE;

  private static final RewindType[] VALUES = values();

  public static RewindType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown RewindType ID: " + ordinal);
  }
}
