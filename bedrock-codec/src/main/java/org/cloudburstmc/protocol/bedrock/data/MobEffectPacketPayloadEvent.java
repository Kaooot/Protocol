package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum MobEffectPacketPayloadEvent {
  /**
   * Invalid
   */
  INVALID,

  /**
   * Add
   */
  ADD,

  /**
   * Update
   */
  UPDATE,

  /**
   * Remove
   */
  REMOVE;

  private static final MobEffectPacketPayloadEvent[] VALUES = values();

  public static MobEffectPacketPayloadEvent from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MobEffectPacketPayloadEvent ID: " + ordinal);
  }
}
