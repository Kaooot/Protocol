package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum AnimatePacketPayloadAction {
  /**
   * NoAction
   */
  NO_ACTION,

  /**
   * Swing
   */
  SWING,

  /**
   * WakeUp
   */
  WAKE_UP,

  /**
   * CriticalHit
   */
  CRITICAL_HIT,

  /**
   * MagicCriticalHit
   */
  MAGIC_CRITICAL_HIT;

  private static final AnimatePacketPayloadAction[] VALUES = values();

  public static AnimatePacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AnimatePacketPayloadAction ID: " + ordinal);
  }
}
