package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CameraAimAssistPacketPayloadAction {
  SET,

  CLEAR;

  private static final CameraAimAssistPacketPayloadAction[] VALUES = values();

  public static CameraAimAssistPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraAimAssistPacketPayloadAction ID: " + ordinal);
  }
}
