package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CameraAimAssistPresetsPacketOperation {
  SET,

  ADD_TO_EXISTING;

  private static final CameraAimAssistPresetsPacketOperation[] VALUES = values();

  public static CameraAimAssistPresetsPacketOperation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraAimAssistPresetsPacketOperation ID: " + ordinal);
  }
}
