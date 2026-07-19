package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum CameraAimAssistPresetsPacketOperation {
  /**
   * Set
   */
  SET,

  /**
   * AddToExisting
   */
  ADD_TO_EXISTING;

  private static final CameraAimAssistPresetsPacketOperation[] VALUES = values();

  public static CameraAimAssistPresetsPacketOperation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraAimAssistPresetsPacketOperation ID: " + ordinal);
  }
}
