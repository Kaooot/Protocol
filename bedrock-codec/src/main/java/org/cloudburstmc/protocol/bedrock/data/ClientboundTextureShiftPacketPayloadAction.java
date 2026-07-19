package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ClientboundTextureShiftPacketPayloadAction {
  /**
   * Invalid
   */
  INVALID,

  /**
   * Initialize
   */
  INITIALIZE,

  /**
   * Start
   */
  START,

  /**
   * SetEnabled
   */
  SET_ENABLED,

  /**
   * Sync
   */
  SYNC;

  private static final ClientboundTextureShiftPacketPayloadAction[] VALUES = values();

  public static ClientboundTextureShiftPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ClientboundTextureShiftPacketPayloadAction ID: " + ordinal);
  }
}
